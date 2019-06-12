package com.kwdz.blog.api.dict.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kwdz.blog.api.common.util.tree.Nodeable;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/30 11:49
 */
@Data
public class SysDictVo implements Serializable, Nodeable {


    private static final long serialVersionUID = -7574689455500980643L;
    private String id;
    private String dictNo;
    private String typeName;
    private String dictValue;
    private String parentId;

    @Override
    public String theId() {
        return id;
    }

    @Override
    public String theParentId() {
        return parentId;
    }

    public static SysDictVo of(String typeName) {
        SysDictVo vo = new SysDictVo();
        vo.setTypeName(typeName);
        return vo;
    }
}
