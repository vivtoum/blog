package com.kwdz.blog.api.common.util;

import org.springframework.web.servlet.ModelAndView;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/23 14:26
 */
public class ErrorPageUtil {

    public static ModelAndView toError(String reason) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("common/error");
        mv.addObject("reason", reason);
        return mv;
    }
}
