package com.carpool.dto;

import com.carpool.entity.CarpoolUser;
import com.carpool.entity.CarpoolCar;

import java.io.Serializable;

/**
 * Created by zuimeideshiguang on 18/2/13.
 */
public class CarpoolUserVo extends CarpoolUser implements Serializable {

    private CarpoolCar carpoolCar;


    public CarpoolCar getCarpoolCar() {
        return carpoolCar;
    }

    public void setCarpoolCar(CarpoolCar carpoolCar) {
        this.carpoolCar = carpoolCar;
    }
}
