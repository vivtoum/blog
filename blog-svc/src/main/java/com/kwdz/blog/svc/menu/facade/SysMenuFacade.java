package com.kwdz.blog.svc.menu.facade;

import com.kwdz.blog.api.menu.vo.SysMenuVo;
import com.kwdz.blog.svc.common.facade.CommonFacade;
import com.kwdz.blog.svc.menu.entity.SysMenuEntity;
import com.kwdz.blog.svc.menu.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author YT.Hu
 */
@Slf4j
@RestController
@RequestMapping("/menu/")
public class SysMenuFacade extends CommonFacade<SysMenuVo, SysMenuEntity> {

    @Autowired
    private MenuService menuService;

    @PostMapping("/tree")
    public String getTreeMenu(String staffCode, String level) {
        return menuService.getTree(staffCode, level);
    }

}
