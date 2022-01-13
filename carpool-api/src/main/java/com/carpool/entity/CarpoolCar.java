package com.carpool.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by zuimeideshiguang on 18/2/13.
 * 车辆信息
 */
@Table(name = "carpool_car")
public class CarpoolCar implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id; // '自增id',
    private Integer userId; // '用户id',
    private String carType; // '车辆类型
    private String color; // 车辆颜色
    private String plateNumberPrefix; // '车牌号前缀',
    private String plateNumber; // '车牌号',
    private String carBrand; //品牌
    private String driverLicense;//驾驶证
    private String drivingLicense;//行驶证
    private Integer seatNums; //车座位数

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public Integer getSeatNums() {
        return seatNums;
    }

    public void setSeatNums(Integer seatNums) {
        this.seatNums = seatNums;
    }
}
