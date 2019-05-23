package com.kwdz.blog.web.controller;


import com.kwdz.blog.api.common.util.IpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/pages/")
public class PagesController {

    @GetMapping("sb2")
    public String sb2() {
        return "sb2/index";
    }

    @PostMapping(value = "/getIp")
    @ResponseBody
    public String getIp(HttpServletRequest request) {
        return IpUtil.getIpAddr(request);
    }
}
