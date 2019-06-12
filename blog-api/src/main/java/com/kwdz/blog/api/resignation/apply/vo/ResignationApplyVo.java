package com.kwdz.blog.api.resignation.apply.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/30 11:49
 */
@Data
public class ResignationApplyVo implements Serializable {


    private static final long serialVersionUID = -3136717152689977997L;

    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastWorkingDay;
    private String resignationReason;
    private String remark;
    private String applicant;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date applyDate;

}
