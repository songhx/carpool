package com.carpool.dto;

import java.io.Serializable;

/**
 * Created by zuimeideshiguang on 18/2/13.
 */
public class CarpoolCarVo implements Serializable {

    private String carType; // '车辆类型  1 轿车 2 SUV  3 出租车',
    private String carColor; // '车辆颜色 1 白色 2 黑色 3 红色  4 银色  5 灰色  6蓝色 7 其他',
    private String carNo; // '车牌号',
    private String carBrand; //品牌


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

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }
}
