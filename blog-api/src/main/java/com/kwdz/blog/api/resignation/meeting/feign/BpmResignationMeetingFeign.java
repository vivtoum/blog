package com.kwdz.blog.api.resignation.meeting.feign;

import com.kwdz.blog.api.common.feign.BaseFeign;
import com.kwdz.blog.api.resignation.meeting.vo.ResignationMeetingVo;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/30 11:55
 */
@FeignClient(name = "blog-svc", path = "/resignationMeeting/", url = "${blog-svc.url}")
public interface BpmResignationMeetingFeign extends BaseFeign<ResignationMeetingVo> {
}
