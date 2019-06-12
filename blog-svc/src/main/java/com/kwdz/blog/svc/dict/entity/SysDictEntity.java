package com.kwdz.blog.svc.dict.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/6/12 12:27
 */
@Entity
@Table(name = "sys_dict")
public class SysDictEntity implements Serializable {
    private static final long serialVersionUID = 7345827174205782440L;
    private String id;
    private String dictNo;
    private String typeName;
    private String dictValue;
    private String parentId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type_name")
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Basic
    @Column(name = "dict_value")
    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    @Basic
    @Column(name = "parent_id")
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysDictEntity that = (SysDictEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(typeName, that.typeName) &&
                Objects.equals(dictValue, that.dictValue) &&
                Objects.equals(parentId, that.parentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeName, dictValue, parentId);
    }


    @Basic
    @Column(name = "dict_no")
    public String getDictNo() {
        return dictNo;
    }

    public void setDictNo(String dcitNo) {
        this.dictNo = dcitNo;
    }
}
