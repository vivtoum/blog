package com.kwdz.blog.api.remoteUser.vo;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Objects;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/24 17:15
 */
@Data
public class RemoteUserVo {
    private String accountType;
    private String employeeNo;
    private String email;
    private String positionName;
    private String orgUnitName;
    private String chineseName;
    private String pinyinName;
    private String englishName;
    private String department;
    private String status;
    private String deptNo;
    private String market;
    private String location;
    private String orgType;
    private String orgLocation;
    private String dateJoin;
    private String grade;

}
