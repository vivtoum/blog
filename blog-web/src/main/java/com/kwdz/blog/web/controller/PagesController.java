package com.kwdz.blog.web.controller;


import com.kwdz.blog.api.common.util.IpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/pages/")
public class PagesController {

    @GetMapping("sb2")
    public String sb2() {
        return "sb2/index";
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
