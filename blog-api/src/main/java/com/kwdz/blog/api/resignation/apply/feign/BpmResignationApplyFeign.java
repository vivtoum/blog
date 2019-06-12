package com.kwdz.blog.api.resignation.apply.feign;

import com.kwdz.blog.api.application.vo.BpmApplicationVo;
import com.kwdz.blog.api.common.feign.BaseFeign;
import com.kwdz.blog.api.resignation.apply.vo.ResignationApplyVo;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/30 11:55
 */
@FeignClient(name = "blog-svc", path = "/resignationApply/", url = "${blog-svc.url}")
public interface BpmResignationApplyFeign extends BaseFeign<ResignationApplyVo> {
}
