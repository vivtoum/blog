package com.kwdz.blog.api.dict.feign;

import com.kwdz.blog.api.application.vo.BpmApplicationVo;
import com.kwdz.blog.api.common.feign.BaseFeign;
import com.kwdz.blog.api.common.result.ResultModel;
import com.kwdz.blog.api.dict.vo.SysDictVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/30 11:55
 */
@FeignClient(name = "blog-svc", path = "/dict/", url = "${blog-svc.url}")
public interface SysDictFeign extends BaseFeign<SysDictVo> {

    @GetMapping("getType")
    ResultModel<List<SysDictVo>> findByTypeName(String typeName);
}
