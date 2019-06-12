package com.kwdz.blog.svc.resignation.apply.service;

import com.kwdz.blog.api.resignation.apply.vo.ResignationApplyVo;
import com.kwdz.blog.svc.common.service.CommonService4RedisImpl;
import com.kwdz.blog.svc.resignation.apply.entity.ResignationApplyEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/30 11:56
 */
@Transactional
@Service
public class ResignationApplyServiceImpl extends CommonService4RedisImpl<ResignationApplyVo, ResignationApplyEntity> implements ResignationApplyService {
}
