package com.kwdz.blog.web.common.error;

import org.springframework.boot.autoconfigure.web.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常工具类
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class ErrorBuilder implements HandlerExceptionResolver, Ordered {

    /**
     * 错误KEY
     */
    private final static String ERROR_NAME = "idc.error";

    /**
     * 错误配置(ErrorConfiguration)
     */
    private ErrorProperties errorProperties;

    public ErrorProperties getErrorProperties() {
        return errorProperties;
    }

    public void setErrorProperties(ErrorProperties errorProperties) {
        this.errorProperties = errorProperties;
    }

    /**
     * 错误构造器 (Constructor) 传递配置属性：server.xx -> server.error.xx
     */
    public ErrorBuilder(ServerProperties serverProperties) {
        this.errorProperties = serverProperties.getError();
    }

    /**
     * 发生错误的URL
     */
    public String getErrorUri(HttpServletRequest request) {
        return (String) request.getAttribute(WebUtils.ERROR_REQUEST_URI_ATTRIBUTE);
    }


    /**
     * 获取错误.(Error/Exception)
     *
     * @see DefaultErrorAttributes #addErrorDetails
     */
    public Throwable getError(HttpServletRequest request) {
        //根据HandlerExceptionResolver接口方法来获取错误.
        Throwable error = (Throwable) request.getAttribute(ERROR_NAME);
        //根据Request对象获取错误.
        if (error == null) {
            error = (Throwable) request.getAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE);
        }
        //当获取错误非空,取出RootCause.
        if (error != null) {
            while (error instanceof ServletException && error.getCause() != null) {
                error = error.getCause();
            }
        }//当获取错误为null,此时我们设置错误信息即可.
        else {
            String message = (String) request.getAttribute(WebUtils.ERROR_MESSAGE_ATTRIBUTE);
            if (StringUtils.isEmpty(message)) {
                HttpStatus status = getHttpStatus(request);
                message = "Unknown Exception But " + status.value() + " " + status.getReasonPhrase();
            }
            error = new Exception(message);
        }
        return error;
    }

    /**
     * 获取通信状态(HttpStatus)
     *
     * @see AbstractErrorController #getStatus
     */
    public HttpStatus getHttpStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE);
        try {
            return statusCode != null ? HttpStatus.valueOf(statusCode) : HttpStatus.INTERNAL_SERVER_ERROR;
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    /**
     * 获取堆栈轨迹(StackTrace)
     *
     * @see DefaultErrorAttributes  #addStackTrace
     */
    public String getStackTraceInfo(Throwable error, boolean flag) {
        if (!flag) {
            return "omitted";
        }
        StringWriter stackTrace = new StringWriter();
        error.printStackTrace(new PrintWriter(stackTrace));
        stackTrace.flush();
        return stackTrace.toString();
    }

    /**
     * 判断是否包含堆栈轨迹.(isIncludeStackTrace)
     *
     * @see BasicErrorController #isIncludeStackTrace
     */
    public boolean isIncludeStackTrace(HttpServletRequest request) {

        //读取错误配置(server.error.include-stacktrace=NEVER)
        ErrorProperties.IncludeStacktrace includeStacktrace = errorProperties.getIncludeStacktrace();

        //情况1：若IncludeStacktrace为ALWAYS
        if (includeStacktrace == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        }
        //情况2：若请求参数含有trace
        if (includeStacktrace == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
            String parameter = request.getParameter("trace");
            return parameter != null && !"false".equals(parameter.toLowerCase());
        }
        //情况3：其它情况
        return false;
    }

    /**
     * 保存错误/异常.
     *
     * @see DispatcherServlet #processHandlerException 进行选举HandlerExceptionResolver
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        request.setAttribute(ERROR_NAME, ex);
        return null;
    }

    /**
     * 提供优先级 或用于排序
     */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
