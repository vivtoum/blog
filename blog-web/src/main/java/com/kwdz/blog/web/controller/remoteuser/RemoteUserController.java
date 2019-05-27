package com.kwdz.blog.web.controller.remoteuser;

import com.kwdz.blog.api.common.controller.BaseController;
import com.kwdz.blog.api.remoteUser.fegin.RemoteUserFeign;
import com.kwdz.blog.api.remoteUser.vo.RemoteUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/27 15:30
 */

@RestController
@RequestMapping("/remoteUser/")
    public class RemoteUserController extends BaseController<RemoteUserVo> {

    @Autowired
    private RemoteUserFeign remoteUserFeign;


}
