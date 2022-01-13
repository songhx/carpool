package com.carpool.dto;

import com.carpool.entity.CarpoolOrder;

import java.io.Serializable;

/**
 * @author bjsonghongxu
 * @create 2018-02-27 17:12
 **/
public class CarpoolOrderVo extends CarpoolOrder implements Serializable {
    private String  departureTimeStr;
    private Integer userType; // '用户类型  0 乘客 1司机',
    private Integer start; // 当前页
    private Integer limit; // 每页条数
    private Integer publishuserId; // 发布人id

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getDepartureTimeStr() {
        return departureTimeStr;
    }

    public void setDepartureTimeStr(String departureTimeStr) {
        this.departureTimeStr = departureTimeStr;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPublishuserId() {
        return publishuserId;
    }

    public void setPublishuserId(Integer publishuserId) {
        this.publishuserId = publishuserId;
    }
}
