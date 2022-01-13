package com.carpool.util;

import com.carpool.dto.CarpoolCarVo;
import com.carpool.entity.CarpoolCar;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zuimeideshiguang on 18/2/13.
 *
 * 拼车工具类
 */
public class CarPoolUtil {
    // '车辆颜色 1 白色 2 黑色 3 红色  4 银色  5 灰色  6蓝色 7 其他',
    public static Map<Integer,String>  COLORMAP = new HashMap<Integer,String>(){{
        put(1,"白色");
        put(2,"黑色");
        put(3,"红色");
        put(4,"银色");
        put(5,"灰色");
        put(6,"蓝色");
        put(7,"紫色");
        put(8,"粉色");
        put(9,"棕色");
        put(10,"其他");
    }};
    // '车辆类型  1 轿车 2 SUV  3 出租车 4 货车 5 其他
    public static Map<Integer,String>  CARTYPEMAP = new HashMap<Integer,String>(){{
        put(1,"轿车");
        put(2,"SUV");
        put(3,"出租车");
        put(4,"货车");
        put(5,"其他");
    }};

    /**
     * 获取车辆信息
     * @param carpoolCar
     * @return
     */
    public static CarpoolCarVo getCarInfo(CarpoolCar carpoolCar){
        if (carpoolCar == null) return null;
        CarpoolCarVo carpoolCarVo = new CarpoolCarVo();
        carpoolCarVo.setCarBrand(carpoolCar.getCarBrand());
        return  carpoolCarVo;
    }
}
