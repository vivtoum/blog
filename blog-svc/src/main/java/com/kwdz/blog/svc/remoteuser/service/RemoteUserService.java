package com.kwdz.blog.svc.remoteuser.service;

import com.kwdz.blog.api.remoteUser.vo.RemoteUserVo;
import com.kwdz.blog.svc.common.service.CommonService;
import com.kwdz.blog.svc.remoteuser.entity.RemoteUserEntity;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/24 11:41
 */
public interface RemoteUserService extends CommonService<RemoteUserVo, RemoteUserEntity> {

    void truncate();
}
