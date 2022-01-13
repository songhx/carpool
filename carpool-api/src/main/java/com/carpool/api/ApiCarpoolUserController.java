package com.carpool.api;

import com.carpool.constants.CommonConstant;
import com.carpool.dto.CarpoolPublishVo;
import com.carpool.entity.CarpoolUser;
import com.carpool.service.ApiCarpoolUserService;
import com.carpool.service.CarpoolCarService;
import com.carpool.util.ApiBaseAction;
import com.carpool.entity.CarpoolCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 拼车用户控制器
 *
 * @author bjsonghongxu
 * @create 2018-02-12 18:02
 **/
@RestController
@RequestMapping("/api/car/user")
public class ApiCarpoolUserController  extends ApiBaseAction {

    @Autowired
    private ApiCarpoolUserService apiCarpoolUserService;

    @Autowired
    private CarpoolCarService carpoolCarService;

    /**
     * 拼车统计
     * @param carpoolPublish
     * @return
     */
    @RequestMapping("stat")
    public Object statCarpoolTimes(CarpoolPublishVo carpoolPublish ) {
        return toResponsSuccess(apiCarpoolUserService.statCarpoolTimes(carpoolPublish));
    }

    /**
     * 查找拼车用户信息
     * @return
     */
    @RequestMapping("queryUser")
    public Object queryUser(CarpoolUser user ) {
        user.setDataStatus(CommonConstant.USEABLE_STATUS);
        CarpoolUser carpoolUser = apiCarpoolUserService.selectOne(user);
        Map<String, Object> resultObj = new HashMap();
        resultObj.put("userInfo",carpoolUser);
        return toResponsSuccess(resultObj);
    }

    /**
     * 查找车辆信息
     * @param car
     * @return
     */
    @RequestMapping("carInfo")
    public Object queryUserCar(CarpoolCar car ) {
        CarpoolCar carpoolCar = carpoolCarService.selectOne(car);
        Map<String, Object> resultObj = new HashMap();
        resultObj.put("carpoolCar",carpoolCar);
        return toResponsSuccess(resultObj);
    }

    /**
     * 完善用户信息
     * @param carpoolUser
     * @return
     */
    @RequestMapping("completUser")
    public Object completUser(@RequestBody CarpoolUser carpoolUser) {

        if (null == carpoolUser.getId()){
            return  toResponsFail("用户在系统中不存在，请先登录！");
        }
        carpoolUser.setUpdateTime(new Date());
        carpoolUser.setIsRealName(CommonConstant.AUTHED);
        apiCarpoolUserService.updateByPrimaryKeySelective(carpoolUser);
        return toResponsSuccess("保存成功");
    }

    /**
     * 车主认证
     * @param carpoolCar
     * @return
     */
    @RequestMapping("completUserCar")
    public Object completUserCar(@RequestBody CarpoolCar carpoolCar) {
        if (null == carpoolCar.getId()){
            carpoolCarService.insertSelective(carpoolCar);
        }else {
            carpoolCarService.updateByPrimaryKeySelective(carpoolCar);
        }
        //修改用户为车主认证成功
        CarpoolUser carpoolUser = new CarpoolUser();
        carpoolUser.setId(carpoolCar.getUserId());
        carpoolUser.setUpdateTime(new Date());
        carpoolUser.setIsAuth(CommonConstant.AUTHED);
        apiCarpoolUserService.updateByPrimaryKeySelective(carpoolUser);

        return toResponsSuccess("保存成功");
    }


}
