package com.kwdz.blog.web.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录信息拦截器
 *
 * @author YT.Hu
 * @date 2019-5-23 15:19:04
 */
@Slf4j
public class LogCostInterceptor implements HandlerInterceptor {
    private long start = System.currentTimeMillis();

    @Value("${url.watsons-ept.login}")
    private static String login_url;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        this.start = System.currentTimeMillis();
        HttpSession session = request.getSession();
        Object ob = session.getAttribute("user");
        if (ob != null) {
            log.info(ob.toString() + "访问HR Process");
            return true;
        }
        session.setAttribute("preurl", request.getRequestURI());
        StringBuffer url = request.getRequestURL();
//        String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getServletContext().getContextPath()).append("/").toString();
        response.sendRedirect(login_url);
        return false;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("校验session花费时间（ms）：" + (System.currentTimeMillis() - this.start));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}