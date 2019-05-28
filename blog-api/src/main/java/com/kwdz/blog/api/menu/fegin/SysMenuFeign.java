package com.kwdz.blog.api.menu.fegin;

import com.kwdz.blog.api.common.feign.BaseFeign;
import com.kwdz.blog.api.menu.vo.SysMenuVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author YT.Hu
 * @version 0.0.1
 * @date 2019/4/17 0:54
 */

@FeignClient(name = "blog-svc", path = "/menu/", url = "${blog-svc.url}")
public interface SysMenuFeign extends BaseFeign<SysMenuVo> {

    /**
     * @param staffCode 员工
     * @param level     等级
     * @return 1
     */
    @PostMapping("/tree")
    String getTreeMenu(@RequestParam("staffCode")String staffCode, @RequestParam("level")String level);
}
