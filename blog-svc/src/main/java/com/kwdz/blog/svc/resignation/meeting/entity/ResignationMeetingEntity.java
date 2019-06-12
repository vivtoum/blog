package com.kwdz.blog.svc.resignation.meeting.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/6/12 12:27
 */
@Entity
@Table(name = "bpm_resignation_meeting")
public class ResignationMeetingEntity {
    private String id;
    private String resignationApplicationId;
    private String mainReason;
    private String workHard;
    private String workHardDetail;
    private String workTime;
    private String workTimeDetail;
    private String workEnv;
    private String workEnvDetail;
    private String workPlace;
    private String workPlaceDetail;
    private String workOffer;
    private String workOfferDetail;
    private String workRelationship;
    private String workRelationshipDetail;
    private String workImprove;
    private String workImproveDetail;
    private String workPersonal;
    private String workPersonalDetail;
    private String advise;
    private String personalEmail;
    private String personalPhone;
    private Integer intentionNewJob;
    private String intentionNewJobType;
    private Integer intentionAgain;
    private Integer intentionResignation;
    private Integer intentionFirend;
    private String interviewerRemark;
    private String interviewerId;
    private Date interviewDate;
    private String applicatId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "resignation_application_id")
    public String getResignationApplicationId() {
        return resignationApplicationId;
    }

    public void setResignationApplicationId(String resignationApplicationId) {
        this.resignationApplicationId = resignationApplicationId;
    }

    @Basic
    @Column(name = "main_reason")
    public String getMainReason() {
        return mainReason;
    }

    public void setMainReason(String mainReason) {
        this.mainReason = mainReason;
    }

    @Basic
    @Column(name = "work_hard")
    public String getWorkHard() {
        return workHard;
    }

    public void setWorkHard(String workHard) {
        this.workHard = workHard;
    }

    @Basic
    @Column(name = "work_hard_detail")
    public String getWorkHardDetail() {
        return workHardDetail;
    }

    public void setWorkHardDetail(String workHardDetail) {
        this.workHardDetail = workHardDetail;
    }

    @Basic
    @Column(name = "work_time")
    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    @Basic
    @Column(name = "work_time_detail")
    public String getWorkTimeDetail() {
        return workTimeDetail;
    }

    public void setWorkTimeDetail(String workTimeDetail) {
        this.workTimeDetail = workTimeDetail;
    }

    @Basic
    @Column(name = "work_env")
    public String getWorkEnv() {
        return workEnv;
    }

    public void setWorkEnv(String workEnv) {
        this.workEnv = workEnv;
    }

    @Basic
    @Column(name = "work_env_detail")
    public String getWorkEnvDetail() {
        return workEnvDetail;
    }

    public void setWorkEnvDetail(String workEnvDetail) {
        this.workEnvDetail = workEnvDetail;
    }

    @Basic
    @Column(name = "work_place")
    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    @Basic
    @Column(name = "work_place_detail")
    public String getWorkPlaceDetail() {
        return workPlaceDetail;
    }

    public void setWorkPlaceDetail(String workPlaceDetail) {
        this.workPlaceDetail = workPlaceDetail;
    }

    @Basic
    @Column(name = "work_offer")
    public String getWorkOffer() {
        return workOffer;
    }

    public void setWorkOffer(String workOffer) {
        this.workOffer = workOffer;
    }

    @Basic
    @Column(name = "work_offer_detail")
    public String getWorkOfferDetail() {
        return workOfferDetail;
    }

    public void setWorkOfferDetail(String workOfferDetail) {
        this.workOfferDetail = workOfferDetail;
    }

    @Basic
    @Column(name = "work_relationship")
    public String getWorkRelationship() {
        return workRelationship;
    }

    public void setWorkRelationship(String workRelationship) {
        this.workRelationship = workRelationship;
    }

    @Basic
    @Column(name = "work_relationship_detail")
    public String getWorkRelationshipDetail() {
        return workRelationshipDetail;
    }

    public void setWorkRelationshipDetail(String workRelationshipDetail) {
        this.workRelationshipDetail = workRelationshipDetail;
    }

    @Basic
    @Column(name = "work_improve")
    public String getWorkImprove() {
        return workImprove;
    }

    public void setWorkImprove(String workImprove) {
        this.workImprove = workImprove;
    }

    @Basic
    @Column(name = "work_improve_detail")
    public String getWorkImproveDetail() {
        return workImproveDetail;
    }

    public void setWorkImproveDetail(String workImproveDetail) {
        this.workImproveDetail = workImproveDetail;
    }

    @Basic
    @Column(name = "work_personal")
    public String getWorkPersonal() {
        return workPersonal;
    }

    public void setWorkPersonal(String workPersonal) {
        this.workPersonal = workPersonal;
    }

    @Basic
    @Column(name = "work_personal_detail")
    public String getWorkPersonalDetail() {
        return workPersonalDetail;
    }

    public void setWorkPersonalDetail(String workPersonalDetail) {
        this.workPersonalDetail = workPersonalDetail;
    }

    @Basic
    @Column(name = "advise")
    public String getAdvise() {
        return advise;
    }

    public void setAdvise(String advise) {
        this.advise = advise;
    }

    @Basic
    @Column(name = "personal_email")
    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    @Basic
    @Column(name = "personal_phone")
    public String getPersonalPhone() {
        return personalPhone;
    }

    public void setPersonalPhone(String personalPhone) {
        this.personalPhone = personalPhone;
    }

    @Basic
    @Column(name = "intention_newJob")
    public Integer getIntentionNewJob() {
        return intentionNewJob;
    }

    public void setIntentionNewJob(Integer intentionNewJob) {
        this.intentionNewJob = intentionNewJob;
    }

    @Basic
    @Column(name = "intention_newJob_type")
    public String getIntentionNewJobType() {
        return intentionNewJobType;
    }

    public void setIntentionNewJobType(String intentionNewJobType) {
        this.intentionNewJobType = intentionNewJobType;
    }

    @Basic
    @Column(name = "intention_again")
    public Integer getIntentionAgain() {
        return intentionAgain;
    }

    public void setIntentionAgain(Integer intentionAgain) {
        this.intentionAgain = intentionAgain;
    }

    @Basic
    @Column(name = "intention_resignation")
    public Integer getIntentionResignation() {
        return intentionResignation;
    }

    public void setIntentionResignation(Integer intentionResignation) {
        this.intentionResignation = intentionResignation;
    }

    @Basic
    @Column(name = "intention_firend")
    public Integer getIntentionFirend() {
        return intentionFirend;
    }

    public void setIntentionFirend(Integer intentionFirend) {
        this.intentionFirend = intentionFirend;
    }

    @Basic
    @Column(name = "interviewer_remark")
    public String getInterviewerRemark() {
        return interviewerRemark;
    }

    public void setInterviewerRemark(String interviewerRemark) {
        this.interviewerRemark = interviewerRemark;
    }

    @Basic
    @Column(name = "interviewer_id")
    public String getInterviewerId() {
        return interviewerId;
    }

    public void setInterviewerId(String interviewerId) {
        this.interviewerId = interviewerId;
    }

    @Basic
    @Column(name = "interview_date")
    public Date getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
    }

    @Basic
    @Column(name = "applicat_id")
    public String getApplicatId() {
        return applicatId;
    }

    public void setApplicatId(String applicatId) {
        this.applicatId = applicatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResignationMeetingEntity that = (ResignationMeetingEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(resignationApplicationId, that.resignationApplicationId) &&
                Objects.equals(mainReason, that.mainReason) &&
                Objects.equals(workHard, that.workHard) &&
                Objects.equals(workHardDetail, that.workHardDetail) &&
                Objects.equals(workTime, that.workTime) &&
                Objects.equals(workTimeDetail, that.workTimeDetail) &&
                Objects.equals(workEnv, that.workEnv) &&
                Objects.equals(workEnvDetail, that.workEnvDetail) &&
                Objects.equals(workPlace, that.workPlace) &&
                Objects.equals(workPlaceDetail, that.workPlaceDetail) &&
                Objects.equals(workOffer, that.workOffer) &&
                Objects.equals(workOfferDetail, that.workOfferDetail) &&
                Objects.equals(workRelationship, that.workRelationship) &&
                Objects.equals(workRelationshipDetail, that.workRelationshipDetail) &&
                Objects.equals(workImprove, that.workImprove) &&
                Objects.equals(workImproveDetail, that.workImproveDetail) &&
                Objects.equals(workPersonal, that.workPersonal) &&
                Objects.equals(workPersonalDetail, that.workPersonalDetail) &&
                Objects.equals(advise, that.advise) &&
                Objects.equals(personalEmail, that.personalEmail) &&
                Objects.equals(personalPhone, that.personalPhone) &&
                Objects.equals(intentionNewJob, that.intentionNewJob) &&
                Objects.equals(intentionNewJobType, that.intentionNewJobType) &&
                Objects.equals(intentionAgain, that.intentionAgain) &&
                Objects.equals(intentionResignation, that.intentionResignation) &&
                Objects.equals(intentionFirend, that.intentionFirend) &&
                Objects.equals(interviewerRemark, that.interviewerRemark) &&
                Objects.equals(interviewerId, that.interviewerId) &&
                Objects.equals(interviewDate, that.interviewDate) &&
                Objects.equals(applicatId, that.applicatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, resignationApplicationId, mainReason, workHard, workHardDetail, workTime, workTimeDetail, workEnv, workEnvDetail, workPlace, workPlaceDetail, workOffer, workOfferDetail, workRelationship, workRelationshipDetail, workImprove, workImproveDetail, workPersonal, workPersonalDetail, advise, personalEmail, personalPhone, intentionNewJob, intentionNewJobType, intentionAgain, intentionResignation, intentionFirend, interviewerRemark, interviewerId, interviewDate, applicatId);
    }
}
