package com.kwdz.blog.web.controller.rsa;

import com.kwdz.blog.api.common.util.RSAUtils;
import com.kwdz.blog.api.common.util.RsaSecurityParameter;
import com.kwdz.blog.api.user.feign.UserFeign;
import com.kwdz.blog.api.user.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author YT.Hu
 */
@Slf4j
@Controller
@RequestMapping("/rsa")
public class RSAController {

    @Autowired
    private UserFeign userFeign;

    @GetMapping("/")
    public String index() {
        return "rsa";
    }

    /**
     * RSA测试
     *
     * @return object
     */
    @PostMapping("/rsaTest")
    @ResponseBody
    @RsaSecurityParameter
    public UserVo rsaTest(@RequestBody UserVo vo) {
        return userFeign.get(vo.getUserId()).getData();
    }
}
