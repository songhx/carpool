package com.carpool.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 评论
 *
 * @author bjsonghongxu
 * @create 2018-06-21 14:49
 **/
@Table(name = "sc_comment")
public class ScComment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // '自增id',

    private Long infoId; // '信息表ID',
    private String avatar; // '头像',
    private String name; // '微信昵称',
    private String content; // '文本内容',
    private Long agreeNum; // '点赞数',
    private Date createTime; // '创建时间',
    private Integer dataStatus; // '数据状态 0 正常 1 删除',


    public ScComment() {
    }

    public ScComment(Long id,Integer dataStatus) {
        this.id = id;
        this.dataStatus = dataStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAgreeNum() {
        return agreeNum;
    }

    public void setAgreeNum(Long agreeNum) {
        this.agreeNum = agreeNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }
}
