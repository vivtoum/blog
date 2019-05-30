package com.kwdz.blog.svc.menu.dao;


import com.kwdz.blog.svc.common.dao.CommonDao;
import com.kwdz.blog.svc.menu.entity.SysMenuEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuDao extends CommonDao<SysMenuEntity> {

    @Query(value = "SELECT * FROM sys_menu where level in('0',?1) order by parent_id,sys_menu.order", nativeQuery = true)
    List<SysMenuEntity> desByOrder(String level);
}
