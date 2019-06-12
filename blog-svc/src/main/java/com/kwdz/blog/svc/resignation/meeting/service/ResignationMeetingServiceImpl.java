package com.kwdz.blog.svc.resignation.meeting.service;

import com.kwdz.blog.api.resignation.meeting.vo.ResignationMeetingVo;
import com.kwdz.blog.svc.common.service.CommonService4RedisImpl;
import com.kwdz.blog.svc.resignation.meeting.entity.ResignationMeetingEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/30 11:56
 */
@Transactional
@Service
public class ResignationMeetingServiceImpl extends CommonService4RedisImpl<ResignationMeetingVo, ResignationMeetingEntity> implements ResignationMeetingService {
}
