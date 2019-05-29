package com.kwdz.blog.web.controller.apply;

import com.kwdz.blog.api.common.util.RsaSecurityParameter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author YT.Hu
 * @version 0.0.1
 * @date 2019/5/29 21:41
 */
@Controller
@RequestMapping("/apply/")
public class ApplyController {


    //    @RsaSecurityParameter
    @PostMapping("resignation")
    public void resignation(ApplyVo data) {
        System.out.println(data);
    }
}
