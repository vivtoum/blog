package com.kwdz.blog.svc.resignation.meeting.facade;

import com.kwdz.blog.api.resignation.meeting.vo.ResignationMeetingVo;
import com.kwdz.blog.svc.common.facade.CommonFacade;
import com.kwdz.blog.svc.resignation.meeting.entity.ResignationMeetingEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 0.0.1
 * @author huyt
 * @date 2019/4/15 0:53
 */

@RestController
@RequestMapping("/resignationMeeting/")
@Slf4j
public class ResignationMeetingFacade extends CommonFacade<ResignationMeetingVo, ResignationMeetingEntity> {

}
