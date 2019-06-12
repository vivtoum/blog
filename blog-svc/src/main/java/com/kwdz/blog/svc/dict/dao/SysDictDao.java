package com.kwdz.blog.svc.dict.dao;

import com.kwdz.blog.svc.application.entity.BpmApplicationEntity;
import com.kwdz.blog.svc.common.dao.CommonDao;
import com.kwdz.blog.svc.dict.entity.SysDictEntity;

import java.util.List;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/30 11:52
 */
public interface SysDictDao extends CommonDao<SysDictEntity> {

    List<SysDictEntity> findByTypeName(String typeName);
}
