package com.kwdz.blog.svc.dict.service;

import com.kwdz.blog.api.common.result.ResultModel;
import com.kwdz.blog.api.common.util.FastCopy;
import com.kwdz.blog.api.dict.vo.SysDictVo;
import com.kwdz.blog.svc.common.service.CommonService4RedisImpl;
import com.kwdz.blog.svc.dict.dao.SysDictDao;
import com.kwdz.blog.svc.dict.entity.SysDictEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/30 11:56
 */
@Transactional
@Service
public class SysDictServiceImpl extends CommonService4RedisImpl<SysDictVo, SysDictEntity> implements SysDictService {
    @Autowired
    private SysDictDao sysDictDao;

    @Override
    public ResultModel<List<SysDictVo>> findByTypeName(String typeName) {
        return ResultModel.of(FastCopy.copyList(sysDictDao.findByTypeName(typeName), SysDictVo.class));
    }
}
