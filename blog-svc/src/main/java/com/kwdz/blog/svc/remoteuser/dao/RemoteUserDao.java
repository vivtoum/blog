package com.kwdz.blog.svc.remoteuser.dao;

import com.kwdz.blog.svc.common.dao.CommonDao;
import com.kwdz.blog.svc.remoteuser.entity.RemoteUserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/24 17:32
 */
public interface RemoteUserDao extends CommonDao<RemoteUserEntity> {

    /**
     * 清空表
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value = "truncate table remote_user" ,nativeQuery = true)
    void delete();
}
