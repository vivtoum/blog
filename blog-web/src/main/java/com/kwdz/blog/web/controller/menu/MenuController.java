package com.kwdz.blog.web.controller.menu;

import com.kwdz.blog.api.common.controller.BaseController;
import com.kwdz.blog.api.menu.fegin.SysMenuFeign;
import com.kwdz.blog.api.menu.vo.SysMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/menu/")
public class MenuController extends BaseController<SysMenuVo> {

    @Autowired
    private SysMenuFeign sysMenuFeign;

    @PostMapping("tree")
    public String getTreeMenu(String staffCode, String level) {
        return sysMenuFeign.getTreeMenu(staffCode, level);
    }

}
