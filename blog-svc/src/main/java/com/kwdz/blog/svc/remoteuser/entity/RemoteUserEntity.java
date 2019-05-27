package com.kwdz.blog.svc.remoteuser.entity;

import com.kwdz.blog.svc.remoteuser.aop.Encry;
import com.kwdz.blog.svc.remoteuser.aop.RemoteUserListen;
import lombok.Data;

import javax.persistence.*;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/24 17:15
 */
@Data
@Entity
@EntityListeners(value = {RemoteUserListen.class})
@Table(name = "remote_user")
public class RemoteUserEntity {
    @Encry(loadDecode = true)
    private String accountType;
    @Id
    private String employeeNo;
    @Encry
    private String email;
    @Encry
    private String positionName;
    @Encry
    private String orgUnitName;
    @Encry(loadDecode = true)
    private String chineseName;
    @Encry(loadDecode = true)
    private String pinyinName;
    @Encry(loadDecode = true)
    private String englishName;
    @Encry
    private String department;
    private String status;
    @Encry
    private String deptNo;
    @Encry
    private String market;
    @Encry
    private String location;
    @Encry
    private String orgType;
    @Encry
    private String orgLocation;
    private String dateJoin;
    @Encry
    private String grade;

}
