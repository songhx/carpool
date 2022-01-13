package com.carpool.service.impl;

import com.carpool.constants.CarpoolConstant;
import com.carpool.constants.CommonConstant;
import com.carpool.entity.*;
import com.carpool.utils.DateUtils;
import com.carpool.utils.GEOUtils;
import com.carpool.utils.StringUtils;
import com.carpool.dao.CarpoolOrderMapper;
import com.carpool.dao.CarpoolPublishMapper;
import com.carpool.dao.CarpoolUserMapper;
import com.carpool.dto.CarpoolOrderVo;
import com.carpool.dto.CarpoolUserOrderVo;
import com.carpool.entity.*;
import com.carpool.service.ApiCarpoolOrderService;
import com.carpool.service.CarpoolOrderLogService;
import com.carpool.service.CarpoolUserFormidService;
import com.carpool.thread.TokenThread;
import com.carpool.weixin.templateMessage.TemplateMsgResult;
import com.carpool.weixin.templateMessage.WechatTemplateMsg;
import com.carpool.weixin.templateMessage.WechatTemplateMsgUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by zuimeideshiguang on 18/2/12.
 */
@Service("apiCarpoolOrderService")
public class ApiCarpoolOrderServiceImpl extends BasicSetServiceImpl<CarpoolOrder> implements ApiCarpoolOrderService {

    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private CarpoolOrderMapper carpoolOrderMapper;
    @Autowired
    private CarpoolPublishMapper carpoolPublishMapper;
    @Autowired
    private CarpoolUserMapper carpoolUserMapper;
    @Autowired
    private CarpoolUserFormidService carpoolUserFormidService;
    @Autowired
    private CarpoolOrderLogService carpoolOrderLogService;

    @Override
    public void order(CarpoolOrderVo carpoolOrder) {
        Date time = new Date();
        carpoolOrder.setCreateTime(time);
        carpoolOrder.setUpdateTime(time);
        carpoolOrder.setStartPointGeo(GEOUtils.cateGeoCode(carpoolOrder.getStartPointLongitude(),carpoolOrder.getStartPointLatitude()));
        carpoolOrder.setDestinationGeo(GEOUtils.cateGeoCode(carpoolOrder.getDestinationLongitude(),carpoolOrder.getDestinationLatitude()));
        carpoolOrder.setDataStatus(CommonConstant.USEABLE_STATUS);
        carpoolOrder.setStatus(CarpoolConstant.ORDERING_STATUS);
        carpoolOrder.setOperatorId(carpoolOrder.getOrderUserId());
        carpoolOrder.setOperatorName(carpoolOrder.getOrderUserName());
        int rs = carpoolOrderMapper.insertSelective(carpoolOrder);

        CarpoolPublish carpoolPublish = new CarpoolPublish();
        carpoolPublish.setId(carpoolOrder.getPublishId());
        carpoolPublish.setDataStatus(CommonConstant.USEABLE_STATUS);
        CarpoolPublish publish = carpoolPublishMapper.selectOne(carpoolPublish);

        if (rs > 0 && null != carpoolOrder.getUserType() && carpoolOrder.getUserType().intValue() == 1){ // 修改发布信息

            if (publish != null){
                if (publish.getPassengerNum() - 1 < 0){
                    throw new RuntimeException("位置已被预订完了");
                }
                carpoolPublish.setPassengerNum(publish.getPassengerNum() - carpoolOrder.getPassengerNum() );
                carpoolPublishMapper.updateByPrimaryKeySelective(carpoolPublish);
                saveLogs(carpoolOrder,carpoolOrder.getOrderUserName() + "预订成功！");


            }
        } else if (rs > 0 && null != carpoolOrder.getUserType() && carpoolOrder.getUserType().intValue() == 0) { // 找车
            CarpoolPublish cp = new CarpoolPublish();
            cp.setId(carpoolOrder.getPublishId());
            cp.setStatus(CarpoolConstant.CONFIRMING_STATUS);
            carpoolPublishMapper.updateByPrimaryKeySelective(cp);
            saveLogs(carpoolOrder,carpoolOrder.getOrderUserName() + "预订成功！");
        }
        /**
         * 给发布人发送拼车请求消息
         */
        //String tmplId = TemplateMessageConstant.CARPOOL_REQUEST_TMPL_ID;
//        String tmplId = publish.getUserType().intValue() == 1 ? TemplateMessageConstant.CARPOOL_REQUEST_TMPL_ID : TemplateMessageConstant.CARPOOL_DRIVER_REQUEST_TMPL_ID; // 模板消息id
//        String page =  "pages/user/orders/orderDetail?id="+carpoolOrder.getId() + "&formType=0";
//
//
//        sendTemplateMsg(publish.getPublishUserId() , tmplId,page, createMsgData(carpoolOrder));
    }

    ///预约消息封装
    private TreeMap<String,TreeMap<String,String>> createMsgData(CarpoolOrderVo carpoolOrder){
        TreeMap<String,TreeMap<String,String>> params = new TreeMap<String,TreeMap<String,String>>();
        List<TreeMap<String,String>> list = new ArrayList<>();
        if (carpoolOrder.getUserType().intValue() == 1){
            list.add(WechatTemplateMsg.item(carpoolOrder.getOrderUserName(), "#000000")); //乘客姓名
            list.add(WechatTemplateMsg.item(DateUtils.format(carpoolOrder.getDepartureTime(),DateUtils.DATE_TIME_PATTERN), "#000000")); //搭乘时间
            list.add(WechatTemplateMsg.item(carpoolOrder.getStartPoint(), "#000000"));//搭乘地点
            list.add(WechatTemplateMsg.item(carpoolOrder.getMobile(), "#000000"));//联系电话
            list.add(WechatTemplateMsg.item(String.valueOf(carpoolOrder.getPassengerNum()), "#000000"));//乘车人数
        }else{
            list.add(WechatTemplateMsg.item(carpoolOrder.getOrderUserName(), "#000000")); //司机姓名
            list.add(WechatTemplateMsg.item(DateUtils.format(carpoolOrder.getDepartureTime(),DateUtils.DATE_TIME_PATTERN), "#000000")); //出发时间
            list.add(WechatTemplateMsg.item(carpoolOrder.getMobile(), "#000000"));//联系电话
            list.add(WechatTemplateMsg.item("请尽快确认拼车信息,保证出行信息无误", "#000000"));//温馨提示
        }
        if(list.size() < 0) return  null;
        for (int i = 0 ; i < list.size() ; i++ ){
            params.put("keyword"+(i+1),list.get(i));
        }

        return params;
    }

    @Override
    public void confirmOrRefuseOrder(CarpoolOrderVo carpoolOrder) {
        logger.info("confirmOrRefuseOrder----- userType --" + carpoolOrder.getUserType());
        Integer status = carpoolOrder.getStatus().intValue();
        Date time = new Date();
        CarpoolOrder co = new CarpoolOrder();
        co.setId(carpoolOrder.getId());
        co.setStatus(status);
        co.setUpdateTime(time);
        carpoolOrderMapper.updateByPrimaryKeySelective(co);

        String msg = status == CarpoolConstant.FINISHED_STATUS ? "确认" : "拒绝";

        /**
         * 给发布人发送拼车请求消息
         */
        ///清空条件查询预约单内容
        CarpoolOrder co1 = new CarpoolOrder();
        co1.setId(carpoolOrder.getId());
        CarpoolOrder order = carpoolOrderMapper.selectOne(co1);

        saveLogs(order,order.getOrderUserName() + msg + "预订！");

        CarpoolPublish cp = new CarpoolPublish();
        cp.setId(order.getPublishId());
        CarpoolPublish cpr =  carpoolPublishMapper.selectOne(cp);

        if (CarpoolConstant.ORDER_REFUSE_STATUS == status ){
            ///将拼车信息回执成发布中
            if (carpoolOrder.getUserType() != null && carpoolOrder.getUserType().intValue() == 0 ){
                CarpoolPublish carpoolPublish = new CarpoolPublish();
                carpoolPublish.setId(order.getPublishId());
                carpoolPublish.setStatus(CarpoolConstant.PUBLISHING_STATUS);
                carpoolPublishMapper.updateByPrimaryKeySelective(carpoolPublish);
            }else if(cpr != null && carpoolOrder.getUserType() != null && carpoolOrder.getUserType().intValue() == 1 && cpr.getStatus() == CarpoolConstant.PUBLISHING_STATUS   ){
                CarpoolPublish carpoolPublish = new CarpoolPublish();
                carpoolPublish.setId(order.getPublishId());
                carpoolPublish.setPassengerNum((cpr.getPassengerNum() != null) ? cpr.getPassengerNum().intValue() + order.getPassengerNum().intValue() : order.getPassengerNum().intValue() );
                carpoolPublishMapper.updateByPrimaryKeySelective(carpoolPublish);
            }
        }
//        String tmplId = (order.getStatus().intValue() == CarpoolConstant.FINISHED_STATUS ? TemplateMessageConstant.CARPOOL_SUCCESS_TMPL_ID : TemplateMessageConstant.CARPOOL_FAIL_TMPL_ID);
//        String page =   "pages/user/orders/orderDetail?id="+order.getId() + "&formType=1"; //"pages/user/orders/orders";
//
//        sendTemplateMsg(order.getOrderUserId() ,tmplId,page, confirmOrRefuseMsgData(order));
    }




    ///预约消息封装
    private TreeMap<String,TreeMap<String,String>> confirmOrRefuseMsgData(CarpoolOrder carpoolOrder){
        TreeMap<String,TreeMap<String,String>> params = new TreeMap<String,TreeMap<String,String>>();
        List<TreeMap<String,String>> list = new ArrayList<>();
        CarpoolPublish carpoolPublish = new CarpoolPublish();
        carpoolPublish.setId(carpoolOrder.getPublishId());
        CarpoolPublish publish = carpoolPublishMapper.selectOne(carpoolPublish);
        if (publish != null){
            CarpoolUser user = new CarpoolUser();
            user.setId(publish.getPublishUserId());
            CarpoolUser carpoolUser = carpoolUserMapper.selectOne(user);
            if (carpoolUser != null){
                if (carpoolOrder.getStatus().intValue() == CarpoolConstant.FINISHED_STATUS ){
                    list.add(WechatTemplateMsg.item(StringUtils.NullToString(carpoolUser.getNickName(),"--"), "#000000")); //司机姓名
                    list.add(WechatTemplateMsg.item(StringUtils.NullToString(carpoolUser.getMobile(),"--"), "#000000"));//司机电话
                    list.add(WechatTemplateMsg.item(StringUtils.NullToString(publish.getPlateNumberPrefix() + publish.getPlateNumber(),
                            "--"), "#000000"));//车牌号码
                    list.add(WechatTemplateMsg.item(DateUtils.format(carpoolOrder.getDepartureTime(),DateUtils.DATE_TIME_PATTERN), "#000000"));//出发时间
                    list.add(WechatTemplateMsg.item(String.valueOf(carpoolOrder.getStartPoint()), "#000000"));//出发地点
                }else{
                    list.add(WechatTemplateMsg.item(carpoolOrder.getStartPoint() +"-"+ carpoolOrder.getDestination(), "#000000")); //当前行程
                    list.add(WechatTemplateMsg.item(DateUtils.format(carpoolOrder.getDepartureTime(),DateUtils.DATE_TIME_PATTERN), "#000000"));//出发时间
                    list.add(WechatTemplateMsg.item(StringUtils.NullToString(carpoolOrder.getRefuseReason(),"--"), "#000000"));//失败原因
                    list.add(WechatTemplateMsg.item("很抱歉，请选择其他拼车信息", "#000000"));//温馨提示
                }
            }
        }
        if(list.size() < 0) return  null;
        for (int i = 0 ; i < list.size() ; i++ ){
            params.put("keyword"+(i+1),list.get(i));
        }
        return params;
    }


    @Override
    public void cancelOrder(CarpoolOrderVo carpoolOrder) {
        logger.info("cancelOrder----- userType --" + carpoolOrder.getUserType());
        CarpoolOrder co = new CarpoolOrder();
        co.setId(carpoolOrder.getId());
        CarpoolOrder order = carpoolOrderMapper.selectOne(co);
        Integer status = order.getStatus();
        if (order != null){
            Date time = new Date();
            CarpoolOrder co1 = new CarpoolOrder();
            co1.setId(carpoolOrder.getId());
            co1.setUpdateTime(time);
            co1.setStatus(CarpoolConstant.ORDER_CANCEL_STATUS);
            int rs = carpoolOrderMapper.updateByPrimaryKeySelective(carpoolOrder);

            CarpoolPublish cp = new CarpoolPublish();
            cp.setId(order.getPublishId());
            CarpoolPublish cpr =  carpoolPublishMapper.selectOne(cp);

            ///将拼车信息回执成发布中
            if (carpoolOrder.getUserType() != null && carpoolOrder.getUserType().intValue() == 0){
                CarpoolPublish carpoolPublish = new CarpoolPublish();
                carpoolPublish.setStatus(CarpoolConstant.PUBLISHING_STATUS);
                carpoolPublish.setId(order.getPublishId());
                carpoolPublishMapper.updateByPrimaryKeySelective(carpoolPublish);
            }else if(carpoolOrder.getUserType() != null && carpoolOrder.getUserType().intValue() == 1 && cpr != null && cpr.getStatus() == CarpoolConstant.PUBLISHING_STATUS){
                CarpoolPublish carpoolPublish = new CarpoolPublish();
                carpoolPublish.setId(order.getPublishId());
                carpoolPublish.setPassengerNum((cpr.getPassengerNum() != null) ? cpr.getPassengerNum().intValue() + order.getPassengerNum().intValue() : order.getPassengerNum().intValue() );
                carpoolPublishMapper.updateByPrimaryKeySelective(carpoolPublish);
            }

            ///预约成功的单子需要给对方发消息
//            if (rs > 0 && status.intValue() == CarpoolConstant.ORDER_SUCCESS_STATUS ){
//                //String page =   "pages/user/records/carpoolRecord";
//                String page =  "pages/user/orders/orderDetail?id="+order.getId() + "&formType=0";
//                sendTemplateMsg(cpr.getPublishUserId() ,TemplateMessageConstant.CARPOOL_ORDER_CANCEL_TMPL_ID,page, cancelMsgData(order));
//            }
        }




    }

    ///预约取消通知封装
    private TreeMap<String,TreeMap<String,String>> cancelMsgData(CarpoolOrder carpoolOrder){
        TreeMap<String,TreeMap<String,String>> params = new TreeMap<String,TreeMap<String,String>>();
        List<TreeMap<String,String>> list = new ArrayList<>();
        CarpoolPublish carpoolPublish = new CarpoolPublish();
        carpoolPublish.setId(carpoolOrder.getPublishId());
        CarpoolPublish publish = carpoolPublishMapper.selectOne(carpoolPublish);
        if (publish != null){
            CarpoolUser user = new CarpoolUser();
            user.setId(publish.getPublishUserId());
            CarpoolUser carpoolUser = carpoolUserMapper.selectOne(user);
            if (carpoolUser != null){
                list.add(WechatTemplateMsg.item(StringUtils.NullToString(carpoolUser.getNickName(),"--"), "#000000")); //姓名
                list.add(WechatTemplateMsg.item(StringUtils.NullToString(carpoolOrder.getMobile(),"--"), "#000000"));//电话
                list.add(WechatTemplateMsg.item(carpoolOrder.getStartPoint() +"-"+ carpoolOrder.getDestination(), "#000000"));// 预约项目
                list.add(WechatTemplateMsg.item(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN), "#000000"));//取消时间
                list.add(WechatTemplateMsg.item(StringUtils.NullToString(carpoolOrder.getCancelReason(),"--"), "#000000"));//取消原因
            }
        }
        if(list.size() < 0) return  null;
        for (int i = 0 ; i < list.size() ; i++ ){
            params.put("keyword"+(i+1),list.get(i));
        }
        return params;
    }

    //记录预约记录
    private void saveLogs(CarpoolOrder carpoolOrder , String content ){
        CarpoolOrderLog log = new CarpoolOrderLog();
        log.setContent(content);
        log.setOrderId(carpoolOrder.getId());
        log.setOperatorId(carpoolOrder.getOrderUserId());
        log.setOperatorName(carpoolOrder.getOrderUserName());
        carpoolOrderLogService.insertSelective(log);
    }

    public void sendTemplateMsg(Integer userId , String tmplId , String page,  TreeMap<String,TreeMap<String,String>> params){
        logger.info("$$$$$send template message userId ----- " + userId);
        logger.info("$$$$$send template message tmplId ----- " + tmplId);
        CarpoolUserFormid formid = new CarpoolUserFormid();
        formid.setUserId(userId); //发布人
        List<CarpoolUserFormid> formidList = carpoolUserFormidService.select(formid);
        if (null != formidList && formidList.size() > 0){
            CarpoolUserFormid userFormid = formidList.get(0);
            logger.info("$$$$$send template message openid ----- " + userFormid.getWxOpenid());
            logger.info("$$$$$send template message formid ----- " + userFormid.getFormId());
            WechatTemplateMsg msg = new WechatTemplateMsg();
            msg.setTouser(userFormid.getWxOpenid());
            msg.setColor("");
            msg.setForm_id(userFormid.getFormId());
            msg.setTemplate_id(tmplId); // 发送模板消息
            msg.setPage(page);
            msg.setEmphasis_keyword("");
            msg.setData(params);
            String token = (TokenThread.accessToken != null) ? TokenThread.accessToken.getAccessToken() : "";
            logger.info("$$$$$send template message token ----- " + token);
            String data = WechatTemplateMsgUtil.createTemplateMsgJson(msg);
            logger.info("$$$$$send template message data ----- " + data);
            TemplateMsgResult tmr = WechatTemplateMsgUtil.sendTemplateMsg(token,data);
            if (tmr != null){
                logger.info("$$send template message rs -" + tmr.getErrmsg()); //输出发送模板消息结果
                CarpoolUserFormid fid = new CarpoolUserFormid();
                fid.setId(userFormid.getId());
                carpoolUserFormidService.delete(fid);
            }
        }
    }

    @Override
    public CarpoolUserOrderVo queryCarpoolUserOrder(Integer id) {
        CarpoolOrder co = new CarpoolOrder();
        co.setDataStatus(CommonConstant.USEABLE_STATUS);
        co.setId(id);
        CarpoolOrder carpoolOrder = carpoolOrderMapper.selectOne(co);
        CarpoolUserOrderVo cuv = null;
        if (carpoolOrder != null){
            cuv = new CarpoolUserOrderVo();
            cuv.setId(carpoolOrder.getId());
            cuv.setOrderBake(carpoolOrder.getBake());
            cuv.setByWays(carpoolOrder.getCancelReason());
            cuv.setCancelReason(carpoolOrder.getCancelReason());
            cuv.setRefuseReason(carpoolOrder.getRefuseReason());
            cuv.setDepartureTime(carpoolOrder.getDepartureTime());
            cuv.setStartPoint(carpoolOrder.getStartPoint());
            cuv.setDestination(carpoolOrder.getDestination());
            cuv.setStatus(carpoolOrder.getStatus());
            cuv.setPassengerNum(carpoolOrder.getPassengerNum());
            setUserInfo(carpoolOrder.getOrderUserId() , null,cuv); //设置预订者信息
            if (carpoolOrder.getPublishId() != null){
                cuv.setPublishId(carpoolOrder.getPublishId());
                CarpoolPublish cp = new CarpoolPublish();
                cp.setId(carpoolOrder.getPublishId());
                cp.setDataStatus(CommonConstant.USEABLE_STATUS);
                CarpoolPublish carpoolPublish = carpoolPublishMapper.selectOne(cp);
                if (carpoolPublish != null){
                    setUserInfo(null , carpoolPublish.getPublishUserId(),cuv); //设置发布者信息
                    cuv.setUserType(carpoolPublish.getUserType());
                    cuv.setPrice(carpoolPublish.getPrice());
                    cuv.setByWays(carpoolPublish.getByWays());
                    cuv.setCarBrand(carpoolPublish.getCarBrand());
                    cuv.setCarColor(carpoolPublish.getCarColor());
                    cuv.setPlateNumberPrefix(carpoolPublish.getPlateNumberPrefix());
                    cuv.setPlateNumber(carpoolPublish.getPlateNumber());
                    cuv.setCarType(carpoolPublish.getCarType());
                    cuv.setPublishBake(carpoolPublish.getBake());
                }
            }

        }
        return cuv;
    }

    @Override
    public int setOrderExpired(Integer publishId) {
        return carpoolOrderMapper.setOrderExpired(publishId);
    }

    private void setUserInfo(Integer orderUserId, Integer publishUserId , CarpoolUserOrderVo cuv){
        CarpoolUser user = new CarpoolUser();
        if (orderUserId != null) user.setId(orderUserId);
        if (publishUserId != null)user.setId(publishUserId);
        CarpoolUser carpoolUser = carpoolUserMapper.selectOne(user);
        if (carpoolUser != null){
            if (orderUserId != null){
                cuv.setOrderUserId(orderUserId);
                cuv.setOrderUserAvatar(carpoolUser.getAvatar());
                cuv.setOrderUsermobile(carpoolUser.getMobile());
                cuv.setOrderUserName(carpoolUser.getNickName());
            }else {
                cuv.setPublishUserId(publishUserId);
                cuv.setPublishUserAvatar(carpoolUser.getAvatar());
                cuv.setPublishUsermobile(carpoolUser.getMobile());
                cuv.setPublishUserName(carpoolUser.getNickName());
            }
        }

    }
}
