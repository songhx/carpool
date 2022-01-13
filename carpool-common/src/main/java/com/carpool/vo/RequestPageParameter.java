package com.carpool.vo;

import java.io.Serializable;

/**
 * 分页请求参数
 *
 * @author bjsonghongxu
 * @create 2017-09-25 15:53
 **/
public class RequestPageParameter extends RequestParameter implements Serializable {

    private Integer start; // 当前页
    private Integer limit; // 每页条数
    private String sort; // 需要排序字段
    private String dir = "ASC"; // 排序方式,默认升序
    private String openId; // '用户标示',

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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
