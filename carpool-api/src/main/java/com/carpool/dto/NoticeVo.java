package com.carpool.dto;

import com.carpool.entity.Notice;

import java.io.Serializable;

/**
 * @author bjsonghongxu
 * @create 2019-03-18 19:29
 **/
public class NoticeVo extends Notice implements Serializable {

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
}
