package com.carpool.entity;

import org.apache.commons.lang.StringUtils;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 拼车用户类
 *
 * @author bjsonghongxu
 * @create 2018-02-12 17:51
 **/
@Table(name = "carpool_user")
public class CarpoolUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // '自增id',
    private String wxOpenid;
    private String userName; // '用户姓名',
    private String nickName;
    private String mobile; // '手机号码',
    private Integer sex; // '性别 0 男 1 女',
    private String avatar; // '头像地址',
    private String province; // '省份',
    private String city; // '地市',
    private String county; // '区',
    private Date createTime; // '创建时间',
    private Date updateTime; // '更新时间',
    private Integer dataStatus; // '数据状态 0  正常  1 删除',
    private Integer isRealName; //是否实名 0 否 1 是
    private Integer isAuth; //是否认证 0 否 1 是
    private String idCardFace; // 身份证正面
    private String idCardBack; // 身份证背面



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = StringUtils.trimToNull(wxOpenid);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = StringUtils.trimToNull(userName);
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = StringUtils.trimToNull(nickName);
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = StringUtils.trimToNull(mobile);
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = StringUtils.trimToNull(province);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = StringUtils.trimToNull(city);
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = StringUtils.trimToNull(county);
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }


    public Integer getIsRealName() {
        return isRealName;
    }

    public void setIsRealName(Integer isRealName) {
        this.isRealName = isRealName;
    }

    public Integer getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(Integer isAuth) {
        this.isAuth = isAuth;
    }

    public String getIdCardFace() {
        return idCardFace;
    }

    public void setIdCardFace(String idCardFace) {
        this.idCardFace = idCardFace;
    }

    public String getIdCardBack() {
        return idCardBack;
    }

    public void setIdCardBack(String idCardBack) {
        this.idCardBack = idCardBack;
    }
}
