package com.kwdz.blog.web.common.error;

import com.kwdz.blog.api.common.error.AccountException;
import com.kwdz.blog.api.common.error.DataException;
import com.kwdz.blog.api.common.result.ResultModel;
import com.kwdz.blog.api.common.util.DateUtil;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.crypto.BadPaddingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author vedyou
 * @version 0.0.1
 * @date: 2019/5/15 19:55
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    ErrorBuilder errorInfoBuilder;

    public static final String DEFAULT_ERROR_VIEW = "/common/sb2/404";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {
        ModelAndView mv = new ModelAndView();
        Throwable ex = errorInfoBuilder.getError(request);
        if (e instanceof DataException) {
            DataException dataException = (DataException) e;
            mv.addObject("exception", dataException.getResultModel());
        } else if (e instanceof FeignException) {
            mv.addObject("exception", ResultModel.of("Feign调用异常", false, ex.getMessage(), errorInfoBuilder.getHttpStatus(request).value()));
        } else if (e instanceof AccountException) {
            mv.addObject("exception", ResultModel.of("登录异常", false, ex.getMessage(), errorInfoBuilder.getHttpStatus(request).value()));
        } else {
            mv.addObject("exception", ResultModel.of("其他异常", false, ex.getMessage(), errorInfoBuilder.getHttpStatus(request).value()));
        }
        log.error("统一异常处理：", ex);
        mv.setViewName(DEFAULT_ERROR_VIEW);
        return mv;
    }

    @ExceptionHandler(value = BadPaddingException.class)
    public ModelAndView cryptoErrorHandler(HttpServletRequest request, BadPaddingException e) {
        ModelAndView mv = new ModelAndView();
        Throwable ex = errorInfoBuilder.getError(request);
        mv.addObject("exception", ResultModel.of("登录信息解密失败", false, ex.getMessage(), errorInfoBuilder.getHttpStatus(request).value()));
        log.error("解密失败：", ex);
        mv.setViewName(DEFAULT_ERROR_VIEW);
        return mv;
    }
}
