package com.carpool.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zuimeideshiguang on 18/2/12.
 */

@Table(name = "carpool_publish")
public class CarpoolPublish implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // '自增id',


    private Integer publishUserId; // '发布人id',
    private Integer userType; // '用户类型  0 乘客 1司机',
    private Integer type; // 拼车类型
    private String mobile; // '联系电话',
    private BigDecimal price;
    private String startPoint; // '具体的出发地点',
    private Double startPointLongitude;
    private Double startPointLatitude;
    private String startPointGeo; // '起点GEO编码',
    private String destination; // '终点',
    private Double destinationLongitude; // '终点经度',
    private Double destinationLatitude; // '终点维度',
    private String destinationGeo; // '终点GEO编码',
    private String byWays; // '途经地方',
    private Integer schedule; // '车程安排  0 单程 1 往返',
    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private Date departureTime; // '出发时间',
    private Integer backDate; // '返程日期',
    private String backTime; // '返程时分（精确到分钟）',
    private Integer passengerNum; // '乘客人数',
    private Integer status; // '拼车信息状态 0 发布中  1 完成  2 取消  3 过期',
    private String cancelReason;//取消原因
    private String bake; // '备注信息',
    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime; // '创建时间',
    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime; // '更新时间',
    private Integer dataStatus; // '数据状态 0  正常  1 删除',

    /************车辆信息*****************/
    private String carType; // '车辆类型
    private String plateNumberPrefix; // '车牌号前缀',
    private String plateNumber; // '车牌号',
    private String carColor; // '车辆颜色
    private String carBrand; //品牌


    @Transient
    private Integer carId;

    @Transient
    private Double distance; //距离

    @Transient
    private String userName;

    @Transient
    private String avatar; // '头像地址',



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPublishUserId() {
        return publishUserId;
    }

    public void setPublishUserId(Integer publishUserId) {
        this.publishUserId = publishUserId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = StringUtils.trimToNull(startPoint);
    }

    public Double getStartPointLongitude() {
        return startPointLongitude != null && startPointLongitude.intValue() !=  0 ? startPointLongitude : null;
    }

    public void setStartPointLongitude(Double startPointLongitude) {
        this.startPointLongitude = startPointLongitude;
    }

    public Double getStartPointLatitude() {
        return startPointLatitude != null && startPointLatitude.intValue() !=  0 ? startPointLatitude : null;
    }

    public void setStartPointLatitude(Double startPointLatitude) {
        this.startPointLatitude = startPointLatitude;
    }

    public String getStartPointGeo() {
        return startPointGeo;
    }

    public void setStartPointGeo(String startPointGeo) {
        this.startPointGeo = startPointGeo;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = StringUtils.trimToNull(destination);
    }

    public Double getDestinationLongitude() {
        return destinationLongitude != null && destinationLongitude.intValue() !=  0 ? destinationLongitude : null;
    }

    public void setDestinationLongitude(Double destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
    }

    public Double getDestinationLatitude() {
        return destinationLatitude != null && destinationLatitude.intValue() !=  0 ? destinationLatitude : null;
    }

    public void setDestinationLatitude(Double destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }

    public String getDestinationGeo() {
        return destinationGeo;
    }

    public void setDestinationGeo(String destinationGeo) {
        this.destinationGeo = destinationGeo;
    }

    public String getByWays() {
        return byWays;
    }

    public void setByWays(String byWays) {
        this.byWays = byWays;
    }

    public Integer getSchedule() {
        return schedule;
    }

    public void setSchedule(Integer schedule) {
        this.schedule = schedule;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getBackDate() {
        return backDate;
    }

    public void setBackDate(Integer backDate) {
        this.backDate = backDate;
    }

    public String getBackTime() {
        return backTime;
    }

    public void setBackTime(String backTime) {
        this.backTime = backTime;
    }

    public Integer getPassengerNum() {
        return passengerNum;
    }

    public void setPassengerNum(Integer passengerNum) {
        this.passengerNum = passengerNum;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBake() {
        return bake;
    }

    public void setBake(String bake) {
        this.bake = bake;
    }

    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
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

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }


    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getPlateNumberPrefix() {
        return plateNumberPrefix;
    }

    public void setPlateNumberPrefix(String plateNumberPrefix) {
        this.plateNumberPrefix = plateNumberPrefix;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
}
