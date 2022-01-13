package com.carpool.dto;

import com.carpool.entity.CarpoolPublish;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zuimeideshiguang on 18/2/26.
 */
public class CarpoolPublishVo extends CarpoolPublish implements Serializable {
    private Double custLongitude;
    private Double custLatitude;
    private Integer start; // 当前页
    private Integer limit; // 每页条数

    private List<Object> geos;
    private List<Object> destinationGeos;
    private String  departureTimeStr;

    public Double getCustLongitude() {
        return custLongitude;
    }

    public void setCustLongitude(Double custLongitude) {
        this.custLongitude = custLongitude;
    }

    public Double getCustLatitude() {
        return custLatitude;
    }

    public void setCustLatitude(Double custLatitude) {
        this.custLatitude = custLatitude;
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

    public List<Object> getGeos() {
        return geos;
    }

    public void setGeos(List<Object> geos) {
        this.geos = geos;
    }

    public List<Object> getDestinationGeos() {
        return destinationGeos;
    }

    public void setDestinationGeos(List<Object> destinationGeos) {
        this.destinationGeos = destinationGeos;
    }

    public String getDepartureTimeStr() {
        return departureTimeStr;
    }

    public void setDepartureTimeStr(String departureTimeStr) {
        this.departureTimeStr = departureTimeStr;
    }
}
