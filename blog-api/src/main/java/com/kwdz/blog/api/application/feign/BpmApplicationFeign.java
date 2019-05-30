package com.kwdz.blog.api.application.feign;

import com.kwdz.blog.api.application.vo.BpmApplicationVo;
import com.kwdz.blog.api.common.feign.BaseFeign;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/30 11:55
 */
@FeignClient(name = "blog-svc", path = "/apply/", url = "${blog-svc.url}")
public interface BpmApplicationFeign extends BaseFeign<BpmApplicationVo> {
}
