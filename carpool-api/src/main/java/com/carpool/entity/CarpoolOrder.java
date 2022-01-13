package com.carpool.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang.StringUtils;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by zuimeideshiguang on 18/2/12.
 */
@Table(name = "carpool_order")
public class CarpoolOrder implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id; // '自增id',

    private Integer publishId; // '行程id',
    private Integer userType; // '用户类型  0 乘客 1司机',
    private Integer orderUserId; // '预约人id',
    private String orderUserName; // '预约/抢单人姓名',
    private String mobile; // '联系电话',
    private Integer passengerNum; // '乘客人数',
    private String startPoint; // '具体的出发地点',
    private Double startPointLongitude;
    private Double startPointLatitude;
    private String startPointGeo; // '起点GEO编码',
    private String destination; // '终点',
    private Double destinationLongitude; // '终点经度',
    private Double destinationLatitude; // '终点维度',
    private String destinationGeo; // '终点GEO编码',
    private Date departureTime; // '出发时间',
    private Integer status; // '状态  0 预约中  1  预约成功  2 拒绝   3 取消  4 失效',
    private String refuseReason; // '拒绝原因',
    private String cancelReason;//取消原因
    private Integer operatorId; // '操作人id',
    private String operatorName; // '操作人姓名',
    private Date createTime; // '创建时间',
    private Date updateTime; // '更新时间',
    private String bake; // '备注信息',
    private Integer dataStatus; // '数据状态  0 正常  1 删除',


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

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = StringUtils.trimToNull(mobile);
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = StringUtils.trimToNull(startPoint);
    }

    public Double getStartPointLongitude() {
        return startPointLongitude;
    }

    public void setStartPointLongitude(Double startPointLongitude) {
        this.startPointLongitude = startPointLongitude;
    }

    public Double getStartPointLatitude() {
        return startPointLatitude;
    }

    public void setStartPointLatitude(Double startPointLatitude) {
        this.startPointLatitude = startPointLatitude;
    }

    public String getStartPointGeo() {
        return startPointGeo;
    }

    public void setStartPointGeo(String startPointGeo) {
        this.startPointGeo = StringUtils.trimToNull(startPointGeo);
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = StringUtils.trimToNull(destination);
    }

    public Double getDestinationLongitude() {
        return destinationLongitude;
    }

    public void setDestinationLongitude(Double destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
    }

    public Double getDestinationLatitude() {
        return destinationLatitude;
    }

    public void setDestinationLatitude(Double destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }

    public String getDestinationGeo() {
        return destinationGeo;
    }

    public void setDestinationGeo(String destinationGeo) {
        this.destinationGeo = StringUtils.trimToNull(destinationGeo);
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
        this.refuseReason = StringUtils.trimToNull(refuseReason);
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = StringUtils.trimToNull(operatorName);
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

    public String getBake() {
        return bake;
    }

    public void setBake(String bake) {
        this.bake = StringUtils.trimToNull(bake);
    }

    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }

    public Integer getPassengerNum() {
        return passengerNum;
    }

    public void setPassengerNum(Integer passengerNum) {
        this.passengerNum = passengerNum;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = StringUtils.trimToNull(cancelReason);
    }
}
