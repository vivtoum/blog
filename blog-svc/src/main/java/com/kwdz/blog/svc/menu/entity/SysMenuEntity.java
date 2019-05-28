package com.kwdz.blog.svc.menu.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "sys_menu")
public class SysMenuEntity {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "url")
    private String url;
    @Column(name = "level")
    private Integer level;
    @Column(name = "order")
    private Integer order;
    @Column(name = "parent_id")
    private String parentId;
    @Column(name = "status")
    private Integer status;
    @Column(name = "icon")
    private String icon;

}
