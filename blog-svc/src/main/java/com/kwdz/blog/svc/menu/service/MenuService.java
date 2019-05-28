package com.kwdz.blog.svc.menu.service;


import com.kwdz.blog.api.menu.vo.SysMenuVo;
import com.kwdz.blog.svc.common.service.CommonService;
import com.kwdz.blog.svc.menu.entity.SysMenuEntity;

public interface MenuService extends CommonService<SysMenuVo, SysMenuEntity> {

    String getTree(String staffCode, String level);
}
