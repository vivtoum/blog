package com.kwdz.blog.svc.remoteUser.facade;

import com.kwdz.blog.api.user.vo.UserVo;
import com.kwdz.blog.svc.common.facade.CommonFacade;
import com.kwdz.blog.svc.remoteUser.service.RemoteUserService;
import com.kwdz.blog.svc.user.entity.UserEntity;
import com.kwdz.blog.svc.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class RemoteUserFacade extends CommonFacade<UserVo, UserEntity> {

    @Autowired
    private RemoteUserService remoteUserService;

    @GetMapping("truncate")
    public void truncate() {
        remoteUserService.truncate();
    }
}
