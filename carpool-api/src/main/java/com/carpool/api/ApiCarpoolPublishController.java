package com.carpool.api;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.carpool.constants.CarpoolConstant;
import com.carpool.constants.CommonConstant;
import com.carpool.dto.CarpoolPublishVo;
import com.carpool.entity.CarpoolCar;
import com.carpool.entity.CarpoolPublish;
import com.carpool.entity.CarpoolUser;
import com.carpool.service.ApiCarpoolUserService;
import com.carpool.service.CarpoolCarService;
import com.carpool.service.CarpoolPublishService;
import com.carpool.util.ApiBaseAction;
import com.carpool.utils.DateUtils;
import com.carpool.utils.GEOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zuimeideshiguang on 18/2/13.
 */
@RestController
@RequestMapping("/api/car/publish")
public class ApiCarpoolPublishController extends ApiBaseAction {


    @Autowired
    private CarpoolCarService carpoolCarService;

    @Autowired
    private CarpoolPublishService carpoolPublishService;

    @Autowired
    private ApiCarpoolUserService apiCarpoolUserService;


    /**
     * 车找人 人找车查询
     * @param carpoolPublish
     * @return
     */
    @RequestMapping("list")
    public Object list(@RequestBody CarpoolPublishVo carpoolPublish) {
        //数据可用
        carpoolPublish.setStatus(CarpoolConstant.PUBLISHING_STATUS); // 发布中
        carpoolPublish.setDataStatus(CommonConstant.USEABLE_STATUS); // 可用

        if (null == carpoolPublish.getUserType()){
            return toResponsFail("未知查询");
        }
        if (StringUtils.isNotBlank(carpoolPublish.getDepartureTimeStr())){carpoolPublish.setDepartureTime(DateUtils.strToDate(carpoolPublish.getDepartureTimeStr()));}
        return toResponsSuccess(carpoolPublishService.queryPublishListByPage(carpoolPublish));
    }

    /**
     * 查询最近的发布
     * @param carpoolPublish
     * @return
     */
    @RequestMapping("latest")
    public Object queryLatest(@RequestBody CarpoolPublishVo carpoolPublish) {
        return toResponsSuccess(carpoolPublishService.queryPublishLatests(carpoolPublish));
    }

    /**
     * 查询拼车发布历史
     * @param carpoolPublish
     * @return
     */
    @RequestMapping("history")
    public Object history(@RequestBody CarpoolPublishVo carpoolPublish) {


        PageHelper.startPage(carpoolPublish.getStart(), carpoolPublish.getLimit(), true, false); //设置分页

        Example example = new Example(CarpoolPublish.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dataStatus",CommonConstant.USEABLE_STATUS);
        if (carpoolPublish.getId() != null){
            criteria.andEqualTo("id",carpoolPublish.getId());
        }
        if (carpoolPublish.getPublishUserId() != null){
            criteria.andEqualTo("publishUserId",carpoolPublish.getPublishUserId());
        }
        if (carpoolPublish.getUserType() != null){
            criteria.andEqualTo("userType",carpoolPublish.getUserType());
        }
        example.setOrderByClause(" updateTime DESC");
        List<CarpoolPublish> list = carpoolPublishService.selectByExample(example);

        PageInfo<CarpoolPublish> pageInfo = new PageInfo<CarpoolPublish>(list);

        Map<String, Object> returnMap = new HashMap<>();
        //设置返回参数
        returnMap.put("totalPage",pageInfo.getPages());
        returnMap.put("list", list);

        return toResponsSuccess(returnMap);
    }

    /**
     * 查询单条行程信息
     * @param carpoolPublish
     * @return
     */
    @RequestMapping("queryTrip")
    public Object queryTrip(@RequestBody CarpoolPublish carpoolPublish) {
        if (null == carpoolPublish.getId()){
            return toResponsFail("参数错误");
        }
        CarpoolPublish publish = new CarpoolPublish();
        publish.setId(carpoolPublish.getId());
        publish.setDataStatus(CommonConstant.USEABLE_STATUS); // 可用
        CarpoolPublish  p =carpoolPublishService.selectOne(publish);
        if (p != null){
            CarpoolUser u = new CarpoolUser();
            u.setId(p.getPublishUserId());
            CarpoolUser user = apiCarpoolUserService.selectOne(u);
            if (user != null){
                p.setUserName(user.getNickName());
                p.setAvatar(user.getAvatar());
            }

        }
        return toResponsSuccess(p);
    }


    /**
     * 发布行程
     * @param carpoolPublish
     * @return
     */
    @RequestMapping("publish")
    public Object publish(@RequestBody  CarpoolPublishVo carpoolPublish) {

        if (null == carpoolPublish.getPublishUserId()){
            return  toResponsFail("用户在系统中不存在，请先登录！");
        }
        //字符串转时间
        fillGEOInfo(carpoolPublish);
        Date time = new Date();
        carpoolPublish.setCreateTime(time);
        carpoolPublish.setUpdateTime(time);
        fillCarInfo(carpoolPublish); // 填充车辆信息

        carpoolPublishService.insertSelective(carpoolPublish);

        return toResponsSuccess("发布成功！");
    }

    //填充信息
    private void fillGEOInfo(CarpoolPublishVo carpoolPublish) {
        if (StringUtils.isNotBlank(carpoolPublish.getDepartureTimeStr())){
            carpoolPublish.setDepartureTime(DateUtils.strToDate(carpoolPublish.getDepartureTimeStr()));
        }
        Date time = new Date();
        carpoolPublish.setStartPointGeo(
                GEOUtils.cateGeoCode(carpoolPublish.getStartPointLongitude(),carpoolPublish.getStartPointLatitude()));
        carpoolPublish.setDestinationGeo(
                GEOUtils.cateGeoCode(carpoolPublish.getDestinationLongitude(),carpoolPublish.getDestinationLatitude()));
    }

    /**
     * 更新行程
     * @param carpoolPublish
     * @return
     */
    @RequestMapping("updateTrip")
    public Object updateTrip(@RequestBody  CarpoolPublishVo carpoolPublish) {

        if (null == carpoolPublish.getId()){
            return  toResponsFail("参数错误！");
        }
        Date time = new Date();
        fillGEOInfo(carpoolPublish);
        carpoolPublish.setUpdateTime(time);
        fillCarInfo(carpoolPublish); // 填充车辆信息

        carpoolPublishService.updateByPrimaryKeySelective(carpoolPublish);

        return toResponsSuccess("更新成功！");
    }


    @RequestMapping("cancel")
    public Object cancelPublish(@RequestBody  CarpoolPublish carpoolPublish) {

        if (null == carpoolPublish.getId()){
            return  toResponsFail("参数不足！");
        }
        Date time = new Date();

        carpoolPublish.setUpdateTime(time);

        carpoolPublishService.cnacelPublish(carpoolPublish);

        return toResponsSuccess("发布成功！");
    }

    //填充车辆信息
    private void fillCarInfo(CarpoolPublish carpoolPublish){
        if (null != carpoolPublish.getUserType() && carpoolPublish.getUserType().intValue() == 1){
            CarpoolCar carpoolCar = null;
            CarpoolCar cc =  new CarpoolCar();
            cc.setUserId(carpoolPublish.getPublishUserId());
            if (carpoolPublish.getCarId() != null) {cc.setId(carpoolPublish.getCarId());}
            List<CarpoolCar> carpoolCarList = carpoolCarService.select(cc);
            if (carpoolCarList != null && carpoolCarList.size() > 0){
                carpoolCar = carpoolCarList.get(0);
            }
            if (null != carpoolCar){
                carpoolPublish.setCarBrand(carpoolCar.getCarBrand());
                carpoolPublish.setCarColor(carpoolCar.getColor());
                carpoolPublish.setPlateNumberPrefix(carpoolCar.getPlateNumberPrefix());
                carpoolPublish.setPlateNumber(carpoolCar.getPlateNumber());
                carpoolPublish.setCarType(carpoolCar.getCarType());
            }
        }

    }
}
