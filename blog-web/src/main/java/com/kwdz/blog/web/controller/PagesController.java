package com.kwdz.blog.web.controller;


import com.kwdz.blog.api.common.util.FastJson;
import com.kwdz.blog.api.common.util.IpUtil;
import com.kwdz.blog.api.menu.fegin.SysMenuFeign;
import com.kwdz.blog.api.menu.vo.SysMenuVo;
import com.kwdz.blog.api.remoteUser.vo.RemoteUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/pages/")
public class PagesController {

    @Autowired
    private SysMenuFeign sysMenuFeign;

    @GetMapping("sb2")
    public ModelAndView sb2(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("sb2/index");
        HttpSession session = request.getSession(false);
        if (session != null) {
            String staffNo = ((RemoteUserVo) session.getAttribute("user")).getEmployeeNo();
            modelAndView.addObject("_menu", FastJson.parseList(sysMenuFeign.getTreeMenu(staffNo, ""), SysMenuVo.class));
        }
        return modelAndView;
    }

    @GetMapping("index")
    public String index(Model model, HttpServletRequest request) {
        model.addAttribute("currentUser", request.getSession().getAttribute("user"));
        // "test"是test.html的名，
        // "iframe_div"是test.html中需要刷新的部分标志,
        // 在标签里加入：th:fragment="iframe_div"
        return "sb2/home::iframe_div";
    }

    @GetMapping("test")
    public String test(Model model, HttpServletRequest request) {
        model.addAttribute("currentUser", request.getSession().getAttribute("user"));
        // "test"是test.html的名，
        // "iframe_div"是test.html中需要刷新的部分标志,
        // 在标签里加入：th:fragment="iframe_div"
        return "sb2/test::iframe_div";
    }

    @PostMapping(value = "/getIp")
    @ResponseBody
    public String getIp(HttpServletRequest request) {
        return IpUtil.getIpAddr(request);
    }
}
