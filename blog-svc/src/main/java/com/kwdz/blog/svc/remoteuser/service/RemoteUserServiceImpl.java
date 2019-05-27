package com.kwdz.blog.svc.remoteuser.service;

import com.kwdz.blog.api.remoteUser.vo.RemoteUserVo;
import com.kwdz.blog.svc.common.service.CommonService4RedisImpl;
import com.kwdz.blog.svc.common.service.CommonServiceImpl;
import com.kwdz.blog.svc.remoteuser.dao.RemoteUserDao;
import com.kwdz.blog.svc.remoteuser.entity.RemoteUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/24 17:32
 */
@Service
public class RemoteUserServiceImpl extends CommonService4RedisImpl<RemoteUserVo, RemoteUserEntity> implements RemoteUserService {

    @Autowired
    private RemoteUserDao dao;

    @Override
    public void truncate() {
        dao.delete();
    }
}
