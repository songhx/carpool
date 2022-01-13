package com.carpool.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 信息表
 *
 * @author bjsonghongxu
 * @create 2018-06-21 14:43
 **/
@Table(name = "sc_info")
public class ScInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // '自增id',
    private String avatar; // '头像',
    private String name; // '微信昵称',
    private Integer type; // '信息类型 1->全部;41->视频;10->图片;29->文本;31->声音',
    private String content; // '文本内容',
    private String imgUrl; // '图片路径，最多支持九张',
    private String videoUrl; // '视频路径',
    private Double width; // '资源宽度',
    private Double height; // '资源高度',
    private String voiceUrl; // '音频路径',
    private String author; // '作者',
    private String bimgUrl; // '音频背景图',
    private Long agreeNum; // '点赞数',
    private Long dissNum; // '踩数',
    private Long repostNum; // '转发数',
    private Long commentNum; // '评论数',
    private Date createTime; // '创建时间',
    private Integer dataStatus; // '数据状态 0 正常 1 删除',

    public ScInfo() {
    }

    public ScInfo(Long id,Integer dataStatus) {
        this.id = id;
        this.dataStatus = dataStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getVoiceUrl() {
        return voiceUrl;
    }

    public void setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBimgUrl() {
        return bimgUrl;
    }

    public void setBimgUrl(String bimgUrl) {
        this.bimgUrl = bimgUrl;
    }

    public Long getAgreeNum() {
        return agreeNum;
    }

    public void setAgreeNum(Long agreeNum) {
        this.agreeNum = agreeNum;
    }

    public Long getDissNum() {
        return dissNum;
    }

    public void setDissNum(Long dissNum) {
        this.dissNum = dissNum;
    }

    public Long getRepostNum() {
        return repostNum;
    }

    public void setRepostNum(Long repostNum) {
        this.repostNum = repostNum;
    }

    public Long getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Long commentNum) {
        this.commentNum = commentNum;
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

    @Override
    public String toString() {
        return "ScInfo{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", voiceUrl='" + voiceUrl + '\'' +
                ", author='" + author + '\'' +
                ", bimgUrl='" + bimgUrl + '\'' +
                ", agreeNum=" + agreeNum +
                ", dissNum=" + dissNum +
                ", repostNum=" + repostNum +
                ", commentNum=" + commentNum +
                ", createTime=" + createTime +
                ", dataStatus=" + dataStatus +
                '}';
    }
}
