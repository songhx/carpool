package com.carpool.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zuimeideshiguang on 18/3/2.
 */
public class CarpoolUserOrderVo implements Serializable {
    private Integer id; // '自增id',

    private Integer publishId; // '行程id',
    private Integer orderUserId; // '预约人id',
    private String orderUserName; // '预约/抢单人姓名',
    private String orderUserAvatar; // 订单用户头像
    private Integer publishUserId; // '预约人id',
    private String publishUserName; // '预约/抢单人姓名',
    private String publishUserAvatar; // 订单用户头像
    private String orderUsermobile; // '订单联系电话',
    private String publishUsermobile; // '发布人联系电话',
    private Integer passengerNum; // '乘客人数',
    private String startPoint; // '具体的出发地点',
    private String destination; // '终点',
    private String byWays; // '途经地方',
    private Date departureTime; // '出发时间',
    private BigDecimal price;
    private Integer status; // '状态  0 预约中  1  预约成功  2 拒绝   3 取消  4 失效 ',
    private String refuseReason; // '拒绝原因',
    private String cancelReason;//取消原因
    private String orderBake; // '备注信息',
    private String publishBake; // '备注信息',
    private String carType; // '车辆类型
    private String carColor; // '车辆颜色
    private String plateNumberPrefix; // '车牌号前缀',
    private String plateNumber; // '车牌号',
    private String carBrand; //品牌
    private Integer userType; // '用户类型  0 乘客 1司机',


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPublishId() {
        return publishId;
    }

    public void setPublishId(Integer publishId) {
        this.publishId = publishId;
    }

    public Integer getOrderUserId() {
        return orderUserId;
    }

    public void setOrderUserId(Integer orderUserId) {
        this.orderUserId = orderUserId;
    }

    public String getOrderUserName() {
        return orderUserName;
    }

    public void setOrderUserName(String orderUserName) {
        this.orderUserName = orderUserName;
    }

    public String getOrderUserAvatar() {
        return orderUserAvatar;
    }

    public void setOrderUserAvatar(String orderUserAvatar) {
        this.orderUserAvatar = orderUserAvatar;
    }

    public Integer getPublishUserId() {
        return publishUserId;
    }

    public void setPublishUserId(Integer publishUserId) {
        this.publishUserId = publishUserId;
    }

    public String getPublishUserName() {
        return publishUserName;
    }

    public void setPublishUserName(String publishUserName) {
        this.publishUserName = publishUserName;
    }

    public String getPublishUserAvatar() {
        return publishUserAvatar;
    }

    public void setPublishUserAvatar(String publishUserAvatar) {
        this.publishUserAvatar = publishUserAvatar;
    }

    public String getOrderUsermobile() {
        return orderUsermobile;
    }

    public void setOrderUsermobile(String orderUsermobile) {
        this.orderUsermobile = orderUsermobile;
    }

    public String getPublishUsermobile() {
        return publishUsermobile;
    }

    public void setPublishUsermobile(String publishUsermobile) {
        this.publishUsermobile = publishUsermobile;
    }

    public Integer getPassengerNum() {
        return passengerNum;
    }

    public void setPassengerNum(Integer passengerNum) {
        this.passengerNum = passengerNum;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getByWays() {
        return byWays;
    }

    public void setByWays(String byWays) {
        this.byWays = byWays;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getOrderBake() {
        return orderBake;
    }

    public void setOrderBake(String orderBake) {
        this.orderBake = orderBake;
    }

    public String getPublishBake() {
        return publishBake;
    }

    public void setPublishBake(String publishBake) {
        this.publishBake = publishBake;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
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
