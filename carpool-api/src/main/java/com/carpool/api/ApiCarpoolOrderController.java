package com.carpool.api;

import com.carpool.constants.CommonConstant;
import com.carpool.dto.CarpoolOrderVo;
import com.carpool.entity.CarpoolOrder;
import com.carpool.service.ApiCarpoolOrderService;
import com.carpool.util.ApiBaseAction;
import com.carpool.utils.DateUtils;
import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zuimeideshiguang on 18/2/13.
 * 预约处理控制器
 */
@RestController
@RequestMapping("/api/car/order")
public class ApiCarpoolOrderController extends ApiBaseAction {

   @Autowired
   private ApiCarpoolOrderService apiCarpoolOrderService;


    /**
     * 预约或者抢单
     * @param carpoolOrder
     * @return
     */
    @RequestMapping("order")
    public Object order(@RequestBody CarpoolOrderVo carpoolOrder) {

        ///校验
        if (null == carpoolOrder.getPublishId()){
            return  toResponsFail("行程不存在！");
        }
        if (null == carpoolOrder.getOrderUserId()){
            return  toResponsFail("用户在系统中不存在，请先登录！");
        }
        //字符串转时间
        if (StringUtils.isNotBlank(carpoolOrder.getDepartureTimeStr())){carpoolOrder.setDepartureTime(DateUtils.strToDate(carpoolOrder.getDepartureTimeStr()));}
        try {
            apiCarpoolOrderService.order(carpoolOrder);
            return toResponsSuccess("预订成功！");
        }catch (Exception e){
            //saveLogs(loginUser,carpoolOrder.getId(),carpoolOrder.getOrderUserName() + "预订失败！");
            return  toResponsFail("预订失败！");
        }

    }


    /**
     * 确认或拒绝
     * @param carpoolOrder
     * @return
     */
    @RequestMapping("confirmOrRefuse")
    public Object confirmOrRefuseOrder(@RequestBody  CarpoolOrderVo carpoolOrder) {

        ///校验
        if (null == carpoolOrder.getId()){
            return  toResponsFail("参数错误！");
        }
        try {
            apiCarpoolOrderService.confirmOrRefuseOrder(carpoolOrder);
            return toResponsSuccess("操作成功！");
        }catch (Exception e){
            //saveLogs(loginUser,carpoolOrder.getId(),carpoolOrder.getOrderUserName() + "预订失败！");
            return  toResponsFail("操作失败！");
        }
    }

    /**
     * 取消预约
     * @param carpoolOrder
     * @return
     */
    @RequestMapping("cancel")
    public Object cancelOrder(@RequestBody  CarpoolOrderVo carpoolOrder) {

        ///校验
        if (null == carpoolOrder.getId()){
            return  toResponsFail("参数错误！");
        }
        try {
            apiCarpoolOrderService.cancelOrder(carpoolOrder);
            return toResponsSuccess("操作成功！");
        }catch (Exception e){
            return  toResponsFail("操作失败！");
        }
    }


    /**
     * 拼车记录
     * @param carpoolOrder
     * @return
     */
    @RequestMapping("list")
    public Object list(@RequestBody  CarpoolOrderVo carpoolOrder) {



        PageHelper.startPage(carpoolOrder.getStart(), carpoolOrder.getLimit(), true, false); //设置分页

        Example example = new Example(CarpoolOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dataStatus", CommonConstant.USEABLE_STATUS);
        if (carpoolOrder.getId() != null){
            criteria.andEqualTo("id",carpoolOrder.getId());
        }
        if (carpoolOrder.getPublishId() != null){
            criteria.andEqualTo("publishId",carpoolOrder.getPublishId());
        }
        if (carpoolOrder.getOrderUserId() != null){
            criteria.andEqualTo("orderUserId",carpoolOrder.getOrderUserId());
        }
        if (carpoolOrder.getUserType() != null){
            criteria.andEqualTo("userType",carpoolOrder.getUserType());
        }
        example.setOrderByClause(" createTime DESC"); ///创建时间倒叙输出
        List<CarpoolOrder> list = apiCarpoolOrderService.selectByExample(example);

        PageInfo<CarpoolOrder> pageInfo = new PageInfo<CarpoolOrder>(list);

        Map<String, Object> returnMap = new HashMap<>();
        //设置返回参数
        returnMap.put("totalPage",pageInfo.getPages());
        returnMap.put("list", list);

        return toResponsSuccess(returnMap);
    }

    /**
     * 查询预约单详情()
     * @param carpoolOrder
     * @return
     */
    @RequestMapping("queryOrder")
    public Object queryOrder(@RequestBody  CarpoolOrderVo carpoolOrder) {
        carpoolOrder.setDataStatus(CommonConstant.USEABLE_STATUS); //可用
        return toResponsSuccess(apiCarpoolOrderService.selectOne(carpoolOrder));
    }

    /**
     * 查询预约单详情(定制)
     * @param carpoolOrder
     * @return
     */
    @RequestMapping("queryUserOrder")
    public Object queryUserOrder(@RequestBody  CarpoolOrderVo carpoolOrder) {
        carpoolOrder.setDataStatus(CommonConstant.USEABLE_STATUS); //可用
        return toResponsSuccess(apiCarpoolOrderService.queryCarpoolUserOrder(carpoolOrder.getId()));
    }
}
