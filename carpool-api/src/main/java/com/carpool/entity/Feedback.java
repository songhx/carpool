package com.carpool.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 微信用户反馈
 *
 * @author bjsonghongxu
 * @create 2017-09-22 17:32
 **/
@Table(name = "carpool_feedback")
public class Feedback implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "SELECT LAST_INSERT_ID()")
    private Integer id; // '自增主键',
    private Integer carpoolId;
    private Integer type; // '类型   1 反馈  2 回复',
    private String title; // '反馈标题',
    private String content; // '反馈内容',
    private String imgUrls;
    private Date feedbackTime; // '反馈时间',
    private Integer wxUserId; // '微信用户id',
    private String wxNickName; // '昵称',
    private String openId; // '用户标示',
    private String formId; // '用户提交反馈formId',
    private Integer replyerId; // '回复人id',
    private String replyerName; // '回复人姓名',
    private String replyContent;
    private Integer replyTo; // '回复反馈的id',
    private Date replyTime; // '回复时间',
    private Integer dataStatus; // '数据状态  0  正常   1 删除',

    @Transient
    private List<Feedback> replyList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlatformId() {
        return carpoolId;
    }

    public void setPlatformId(Integer carpoolId) {
        this.carpoolId = carpoolId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(Date feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public Integer getWxUserId() {
        return wxUserId;
    }

    public void setWxUserId(Integer wxUserId) {
        this.wxUserId = wxUserId;
    }

    public String getWxNickName() {
        return wxNickName;
    }

    public void setWxNickName(String wxNickName) {
        this.wxNickName = wxNickName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public Integer getReplyerId() {
        return replyerId;
    }

    public void setReplyerId(Integer replyerId) {
        this.replyerId = replyerId;
    }

    public String getReplyerName() {
        return replyerName;
    }

    public void setReplyerName(String replyerName) {
        this.replyerName = replyerName;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Integer getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(Integer replyTo) {
        this.replyTo = replyTo;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }

    public List<Feedback> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Feedback> replyList) {
        this.replyList = replyList;
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }
}
