package com.kwdz.blog.svc.application.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kwdz.blog.api.common.aop.Encry;
import com.kwdz.blog.svc.common.aop.JpaEntityListen;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/30 11:49
 */
@Data
@EntityListeners(value = {JpaEntityListen.class})
@Entity
@Table(name = "bpm_application")
public class BpmApplicationEntity implements Serializable {

    private static final long serialVersionUID = 405224420788296904L;

    @Id
    @Column(name = "id")

    private String id;
    @Column(name = "no")
    private String no;

    @Column(name = "type")
    private String type;

    @Column(name = "form_data")
    @Encry
    private String formData;

    @Column(name = "operator_id")
    private String operatorId;

    @Column(name = "operator_ip")
    private String operatorIp;

    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @Column(name = "status")
    private String status;

    @Column(name = "bpm_id")
    private String bpmId;

}
