package com.carpool.dto;

import java.io.Serializable;

/**
 * 拼车统计
 *
 * @author bjsonghongxu
 * @create 2019-03-20 15:35
 **/
public class StatCarpoolVo implements Serializable {

    private Integer userType;
    private Integer num;

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
