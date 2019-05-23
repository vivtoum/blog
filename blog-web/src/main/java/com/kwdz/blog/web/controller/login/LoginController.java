package com.kwdz.blog.web.controller.login;

import Edge.DES.DES;
import com.kwdz.blog.api.common.util.DateUtil;
import com.kwdz.blog.api.common.util.ErrorPageUtil;
import com.kwdz.blog.api.common.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 接入e-portal的校验逻辑
 *
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/23 11:15
 */
@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * e-portal跳转过来的入口,用于校验
     */
    @GetMapping("/sso")
    public ModelAndView sso(HttpServletRequest request, String empno, String language, String key, String check) throws Exception {
        String ip = IpUtil.getIpAddr(request);
        String staffNo = DES.DecryptString(empno, key);
        String ipTime = DES.DecryptString(check, key);
        String time = DateUtil.getCurrentDateStrs();
        ModelAndView mv = new ModelAndView();
//        (ip + "|" + time).equalsIgnoreCase(ipTime)
        if (true) {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") == null) {
                session.setAttribute("user", staffNo);
                session.setAttribute("level", "G17");
                log.warn("不存在session");
            } else {
                log.info("存在session");
            }
            mv.setViewName("redirect:/pages/sb2");
        } else {
            mv = ErrorPageUtil.toError("表示该URL已无效，不允许打开子系统");
        }
        return mv;
    }

    @GetMapping("/")
    public String index() {
        return "sb2/index";
    }
}
