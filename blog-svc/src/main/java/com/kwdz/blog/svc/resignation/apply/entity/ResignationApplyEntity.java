package com.kwdz.blog.svc.resignation.apply.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/6/12 12:27
 */
@Entity
@Table(name = "bpm_resignation_apply")
public class ResignationApplyEntity implements Serializable {

    private static final long serialVersionUID = -7155185434410470007L;
    private String id;
    private Date lastWorkingDay;
    private String resignationReason;
    private String remark;
    private String applicant;
    private Date applyDate;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "last_working_day")
    public Date getLastWorkingDay() {
        return lastWorkingDay;
    }

    public void setLastWorkingDay(Date lastWorkingDay) {
        this.lastWorkingDay = lastWorkingDay;
    }

    @Basic
    @Column(name = "resignation_reason")
    public String getResignationReason() {
        return resignationReason;
    }

    public void setResignationReason(String resignationReason) {
        this.resignationReason = resignationReason;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "applicant")
    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    @Basic
    @Column(name = "apply_date")
    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResignationApplyEntity that = (ResignationApplyEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(lastWorkingDay, that.lastWorkingDay) &&
                Objects.equals(resignationReason, that.resignationReason) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(applicant, that.applicant) &&
                Objects.equals(applyDate, that.applyDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastWorkingDay, resignationReason, remark, applicant, applyDate);
    }
}
