package com.kwdz.blog.svc.remoteUser.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/24 17:15
 */
@Entity
@Table(name = "remote_user", schema = "springbootdemo", catalog = "")
public class RemoteUserEntity {
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

    @Basic
    @Column(name = "account_type")
    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Id
    @Column(name = "employee_no")
    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "position_name")
    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    @Basic
    @Column(name = "org_unit_name")
    public String getOrgUnitName() {
        return orgUnitName;
    }

    public void setOrgUnitName(String orgUnitName) {
        this.orgUnitName = orgUnitName;
    }

    @Basic
    @Column(name = "chinese_name")
    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    @Basic
    @Column(name = "pinyin_name")
    public String getPinyinName() {
        return pinyinName;
    }

    public void setPinyinName(String pinyinName) {
        this.pinyinName = pinyinName;
    }

    @Basic
    @Column(name = "english_name")
    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    @Basic
    @Column(name = "Department")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Basic
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "Dept_No")
    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    @Basic
    @Column(name = "Market")
    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "org_type")
    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    @Basic
    @Column(name = "org_location")
    public String getOrgLocation() {
        return orgLocation;
    }

    public void setOrgLocation(String orgLocation) {
        this.orgLocation = orgLocation;
    }

    @Basic
    @Column(name = "date_join")
    public String getDateJoin() {
        return dateJoin;
    }

    public void setDateJoin(String dateJoin) {
        this.dateJoin = dateJoin;
    }

    @Basic
    @Column(name = "grade")
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemoteUserEntity that = (RemoteUserEntity) o;
        return employeeNo == that.employeeNo &&
                Objects.equals(accountType, that.accountType) &&
                Objects.equals(email, that.email) &&
                Objects.equals(positionName, that.positionName) &&
                Objects.equals(orgUnitName, that.orgUnitName) &&
                Objects.equals(chineseName, that.chineseName) &&
                Objects.equals(pinyinName, that.pinyinName) &&
                Objects.equals(englishName, that.englishName) &&
                Objects.equals(department, that.department) &&
                Objects.equals(status, that.status) &&
                Objects.equals(deptNo, that.deptNo) &&
                Objects.equals(market, that.market) &&
                Objects.equals(location, that.location) &&
                Objects.equals(orgType, that.orgType) &&
                Objects.equals(orgLocation, that.orgLocation) &&
                Objects.equals(dateJoin, that.dateJoin) &&
                Objects.equals(grade, that.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountType, employeeNo, email, positionName, orgUnitName, chineseName, pinyinName, englishName, department, status, deptNo, market, location, orgType, orgLocation, dateJoin, grade);
    }
}
