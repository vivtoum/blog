package com.kwdz.blog.svc.remoteuser.facade;

import com.kwdz.blog.api.remoteUser.vo.RemoteUserVo;
import com.kwdz.blog.svc.common.facade.CommonFacade;
import com.kwdz.blog.svc.remoteuser.entity.RemoteUserEntity;
import com.kwdz.blog.svc.remoteuser.service.RemoteUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huyt
 * @version 0.0.1
 * @date 2019/4/15 0:53
 */

@RestController
@RequestMapping("/remoteUser/")
@Slf4j
public class RemoteUserFacade extends CommonFacade<RemoteUserVo, RemoteUserEntity> {

    @Autowired
    private RemoteUserService remoteUserService;

    @GetMapping("truncate")
    public void truncate() {
        remoteUserService.truncate();
    }
}
