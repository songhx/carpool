package com.carpool.page;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName PageVo
 * @Description 分页vo
 * @author bjlisuxiao
 * @date 2017/3/13 18:17
 */
public class PageVo implements Serializable {

    private static final long serialVersionUID = -5187402637394579368L;

    private int start; // 页索引
    private int limit; // 页容量

    private Long total;//总数
    private Integer rs;//结果标识：1-成功；2-失败；3-警告
    private String info;//结果说明

    private List list;//返回数据

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getRs() {
        return rs;
    }

    public void setRs(Integer rs) {
        this.rs = rs;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
