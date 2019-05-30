package com.kwdz.blog.svc.application.service;

import com.kwdz.blog.api.application.vo.BpmApplicationVo;
import com.kwdz.blog.svc.application.entity.BpmApplicationEntity;
import com.kwdz.blog.svc.common.service.CommonService4RedisImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/30 11:56
 */
@Transactional
@Service
public class BpmApplicationServiceImpl extends CommonService4RedisImpl<BpmApplicationVo, BpmApplicationEntity> implements BpmApplicationService {
}
