package com.kwdz.blog.web.common.interceptor;

import com.kwdz.blog.api.common.properties.PropertiesListenerConfig;
import com.kwdz.blog.api.remoteUser.vo.RemoteUserVo;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        this.start = System.currentTimeMillis();
        HttpSession session = request.getSession();
        RemoteUserVo ob = (RemoteUserVo) session.getAttribute("user");
        if (ob != null) {
            log.info("┏━━━━━━━ 登录信息校验 START ━━━━━━━┓");
            log.info("             " + ob.getChineseName() + " 访问 HR Process");
            return true;
        }
        session.setAttribute("preurl", request.getRequestURI());
        response.sendRedirect(PropertiesListenerConfig.propertiesMap.get("watsons.ept.login").toString());
        return false;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("           校验session花费时间（ms）：" + (System.currentTimeMillis() - this.start));
        log.info("┗━━━━━━━ 登录信息校验  END  ━━━━━━━┛");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}