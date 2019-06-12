package com.kwdz.blog.svc.dict.service;

import com.kwdz.blog.api.common.result.ResultModel;
import com.kwdz.blog.api.dict.vo.SysDictVo;
import com.kwdz.blog.svc.common.service.CommonService;
import com.kwdz.blog.svc.dict.entity.SysDictEntity;

import java.util.List;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/30 11:53
 */
public interface SysDictService extends CommonService<SysDictVo, SysDictEntity> {

    ResultModel<List<SysDictVo>> findByTypeName(String typeName);
}
