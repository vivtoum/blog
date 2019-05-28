package com.kwdz.blog.api.menu.vo;

import lombok.Data;

import java.util.List;

/**
 * @author YT.Hu
 */
@Data
public class SysMenuVo {

    private String id;
    private String name;
    private String url;
    private Integer level;
    private Integer order;
    private String parentId;
    private Integer status;
    private String icon;

    private List<SysMenuVo> childMenus;

}
