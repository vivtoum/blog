package com.kwdz.blog.api.application.feign;

import com.kwdz.blog.api.application.vo.BpmApplicationVo;
import com.kwdz.blog.api.common.feign.BaseFeign;
import com.kwdz.blog.api.common.result.ResultModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/30 11:55
 */
@FeignClient(name = "blog-svc", path = "/apply/", url = "${blog-svc.url}")
public interface BpmApplicationFeign extends BaseFeign<BpmApplicationVo> {

    @PostMapping("resignation")
    ResultModel applyResignation(@RequestBody BpmApplicationVo data);
}
