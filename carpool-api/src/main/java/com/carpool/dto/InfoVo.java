package com.carpool.dto;

import com.carpool.entity.ScInfo;

import java.io.Serializable;

/**
 * @author bjsonghongxu
 * @create 2018-06-21 18:47
 **/
public class InfoVo extends ScInfo implements Serializable {

    private Integer start; // 当前页
    private Integer limit; // 每页条数

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

    @Override
    public String toString() {
        return "InfoVo{" +
                "start=" + start +
                ", limit=" + limit +
                '}';
    }
}
