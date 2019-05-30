package com.kwdz.blog.api.application.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/30 11:49
 */
@Data
public class BpmApplicationVo implements Serializable {

    private static final long serialVersionUID = 405224420788296904L;

    private String id;
    private String no;
    private String type;
    private String formData;
    private String operatorId;
    private String operatorIp;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private String status;
    private String bpmId;

}
