/*
 * 版权所有 © 成都太阳高科技有限责任公司
 * http://www.suncd.com
 */
package com.kwdz.blog.web.controller.pdf;

import com.kwdz.blog.api.remoteUser.vo.RemoteUserVo;
import com.kwdz.blog.web.common.util.PdfUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 功能：pdf预览、下载
 *
 * @author YT.Hu
 * @version 1.0 2018/2/23 9:35
 */
@Controller
@RequestMapping(value = "/pdf")
public class PdfController {

    @GetMapping(value = "/")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("pdf");
        modelAndView.addObject("title", "CGX");
        return modelAndView;
    }

    @Autowired
    private FreeMarkerConfigurer configurer;

    /**
     * pdf预览
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     */
    @GetMapping(value = "/preview")
    public void preview(HttpServletRequest request, HttpServletResponse response) {
        RemoteUserVo user = (RemoteUserVo) request.getSession().getAttribute("user");
        // 构造freemarker模板引擎参数,listVars.size()个数对应pdf页数
        List<Map<String, Object>> listVars = new ArrayList<>();
        Map<String, Object> variables = new HashMap<>(16);
        variables.put("title", "测试预览ASGX!");
        variables.put("nowDate", new Date());
        variables.put("currentUser", user);
        listVars.add(variables);

        PdfUtils.preview(configurer, "pdfPage.ftl", listVars, response);
    }

    /**
     * pdf下载
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     */
    @GetMapping(value = "/download")
    public void download(HttpServletRequest request, HttpServletResponse response) {
        List<Map<String, Object>> listVars = new ArrayList<>();
        Map<String, Object> variables = new HashMap<>(16);
        variables.put("title", "测试下载ASGX!");
        listVars.add(variables);
        PdfUtils.download(configurer, "pdfPage.ftl", listVars, response, "测试中文.pdf");
    }
}
