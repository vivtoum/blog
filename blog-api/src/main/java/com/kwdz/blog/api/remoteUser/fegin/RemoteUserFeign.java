package com.kwdz.blog.api.remoteUser.fegin;

import com.kwdz.blog.api.common.feign.BaseFeign;
import com.kwdz.blog.api.remoteUser.vo.RemoteUserVo;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @version 0.0.1
 * @author YT.Hu
 * @date 2019/4/17 0:54
 */

@FeignClient(name = "blog-svc", path = "/remoteUser/", url = "${blog-svc.url}")
public interface RemoteUserFeign extends BaseFeign<RemoteUserVo>{

}
