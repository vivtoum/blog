package com.kwdz.blog.web.controller.i18n;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/6/4 17:24
 */
@Controller
@RequestMapping("/language/")
public class I18nController {

    @GetMapping("/{language}")
    public String language(HttpServletRequest request, HttpServletResponse response, @PathVariable String language) {
        //打印日志
        Locale locale = request.getLocale();
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if (language == null || language.equals("")) {
            return "index";
        } else {
            if (language.equals("CN")) {
                //会话区域解析器
                localeResolver.setLocale(request, response, Locale.CHINA);
            } else if (language.equals("US")) {
                localeResolver.setLocale(request, response, Locale.US);
            } else {
                localeResolver.setLocale(request, response, Locale.CHINA);
            }
        }
        return "/pages/sb2";
    }
}
