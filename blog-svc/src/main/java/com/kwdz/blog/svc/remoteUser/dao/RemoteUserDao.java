package com.kwdz.blog.svc.remoteUser.dao;

import com.kwdz.blog.svc.common.dao.CommonDao;
import com.kwdz.blog.svc.remoteUser.entity.RemoteUserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

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
    @Query(value = "delete from remote_user" ,nativeQuery = true)
    void delete();
}
