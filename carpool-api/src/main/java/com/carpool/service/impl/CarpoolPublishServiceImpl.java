package com.carpool.service.impl;

import com.carpool.constants.CarpoolConstant;
import com.carpool.constants.CommonConstant;
import com.carpool.entity.CarpoolUser;
import com.carpool.utils.DateUtils;
import com.carpool.utils.GEOUtils;
import com.carpool.utils.StringUtils;
import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.carpool.dao.CarpoolOrderMapper;
import com.carpool.dao.CarpoolPublishMapper;
import com.carpool.dao.CarpoolUserMapper;
import com.carpool.dto.CarpoolPublishVo;
import com.carpool.entity.CarpoolOrder;
import com.carpool.entity.CarpoolPublish;
import com.carpool.service.ApiCarpoolOrderService;
import com.carpool.service.CarpoolPublishService;
import com.carpool.weixin.templateMessage.WechatTemplateMsg;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zuimeideshiguang on 18/2/12.
 */
@Service("carpoolPublishService")
public class CarpoolPublishServiceImpl extends BasicSetServiceImpl<CarpoolPublish> implements CarpoolPublishService {

    @Autowired
    private CarpoolPublishMapper carpoolPublishMapper;

    @Autowired
    private CarpoolOrderMapper carpoolOrderMapper;

    @Autowired
    private CarpoolUserMapper carpoolUserMapper;

    @Autowired
    private ApiCarpoolOrderService apiCarpoolOrderService;

    @Override
    public Map<String, Object> queryPublishListByPage(CarpoolPublishVo carpoolPublish) {
        List<Object> geos = null;

        ///起点地理查询范围设置
        if(carpoolPublish.getStartPointLatitude() != null && carpoolPublish.getStartPointLongitude() != null){
            carpoolPublish.setCustLatitude(carpoolPublish.getStartPointLatitude());
            carpoolPublish.setCustLongitude(carpoolPublish.getStartPointLongitude());
        }
        if (carpoolPublish.getCustLatitude() != null && carpoolPublish.getCustLongitude() != null){
            geos = GEOUtils.createGeoCodes(carpoolPublish.getCustLongitude(),carpoolPublish.getCustLatitude());
        }
        carpoolPublish.setGeos(geos); // 设置地理查询范围

        ///设置终点地理范围
    /*    if (carpoolPublish.getDestinationLatitude() != null && carpoolPublish.getDestinationLongitude() != null){
            carpoolPublish.setDestinationGeos(GEOUtils.createGeoCodes(carpoolPublish.getDestinationLongitude(),carpoolPublish.getDestinationLatitude()));
        }*/

        List<CarpoolPublish> datas = carpoolPublishMapper.selectTrips(carpoolPublish);
        Map<String, Object> returnMap = new HashMap<>();
        //设置返回参数
        int total = datas != null ? datas.size() : 0;
        int page = total / carpoolPublish.getLimit().intValue() + ((total % carpoolPublish.getLimit().intValue() == 0) ? 0 : 1);
        returnMap.put("totalPage",page);
        returnMap.put("list", pageAndSort(carpoolPublish,datas));
        return returnMap;
    }

    @Override
    public void cnacelPublish(CarpoolPublish carpoolPublish) {

        carpoolPublish.setStatus(CarpoolConstant.CANCEL_STATUS);
        int rs = carpoolPublishMapper.updateByPrimaryKeySelective(carpoolPublish);

        if (rs > 0  ){ // 发送模板消息
            Example example = new Example(CarpoolOrder.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("dataStatus", CommonConstant.USEABLE_STATUS);
            List<Object> list = new ArrayList<>();
            list.add(CarpoolConstant.ORDERING_STATUS); //预约中
            list.add(CarpoolConstant.ORDER_SUCCESS_STATUS); //预约中
            criteria.andIn("status",list);
            List<CarpoolOrder> carpoolOrders = carpoolOrderMapper.selectByExample(example); // 预约成功和预约中的订单
            if(carpoolOrders != null && carpoolOrders.size() > 0){
                for (CarpoolOrder carpoolOrder : carpoolOrders){
                    CarpoolOrder order = new CarpoolOrder();
                    order.setId(carpoolOrder.getId());
                    order.setStatus(CarpoolConstant.ORDER_CANCEL_STATUS);
                    order.setCancelReason("很抱歉因行程取消,预约被取消");
                    carpoolOrderMapper.updateByPrimaryKeySelective(order);
//                    String page =  "pages/user/orders/orderDetail?id="+carpoolOrder.getId() + "&formType=1";
//                    apiCarpoolOrderService.sendTemplateMsg(carpoolOrder.getOrderUserId(), TemplateMessageConstant.CARPOOL_PUBLISH_CANCEL_TMPL_ID,page,cancelMsgData(carpoolPublish,carpoolOrder));
                }
            }


        }
    }

    @Override
    public void dealPublishAndOrderStatus() {
        Example example = new Example(CarpoolPublish.class);
        Example.Criteria criteria = example.createCriteria();
        List<Object> list = new ArrayList<>();
        list.add(CarpoolConstant.PUBLISHING_STATUS);
        list.add(CarpoolConstant.CONFIRMING_STATUS);
        criteria.andIn("status",list);
        List<CarpoolPublish> carpoolPublishList = carpoolPublishMapper.selectByExample(example);
        Date time = new Date();
        if (null != carpoolPublishList && carpoolPublishList.size() > 0 ){
            List<Integer> expireList = new ArrayList<>();
            List<Integer> doneList = new ArrayList<>();
          for (CarpoolPublish p : carpoolPublishList){
              if ( p.getDepartureTime() != null && DateUtils.pastMinutes(p.getDepartureTime()) >= 0 ){
                 if(null != p.getUserType() && p.getUserType().intValue() == 1
                         && p.getPassengerNum() != null && p.getPassengerNum().intValue() == 0
                         ){
                     doneList.add(p.getId());
                 }else{
                     CarpoolOrder co = new CarpoolOrder();
                     co.setDataStatus(0);
                     co.setStatus(CarpoolConstant.ORDERING_STATUS);
                     int rs =  apiCarpoolOrderService.selectCount(co);
                     if(rs > 0){
                         expireList.add(p.getId());
                     }else {
                         doneList.add(p.getId());
                     }
                  }

              }
          }
          ///处理过期
          if (expireList.size() > 0){
              for (Integer id : expireList){
                  CarpoolPublish cpe = new CarpoolPublish();
                  cpe.setId(id);
                  cpe.setStatus(CarpoolConstant.EXPIRE_STATUS);
                  carpoolPublishMapper.updateByPrimaryKeySelective(cpe);

                  ////预约中的单子置为过期
                  apiCarpoolOrderService.setOrderExpired(id);
              }

          }

          if (doneList.size() > 0){
              for (Integer id : doneList){
                  CarpoolPublish cpe = new CarpoolPublish();
                  cpe.setStatus(CarpoolConstant.FINISHED_STATUS);
                  cpe.setId(id);
                  carpoolPublishMapper.updateByPrimaryKeySelective(cpe);

              }

          }
        }
    }

    @Override
    public Map<String, Object> queryPublishLatests(CarpoolPublishVo carpoolPublish) {
        Map<String, Object> map = new HashMap<>();
        map.put("carpoolPublish", queryCarpoolPublishInfos(carpoolPublish,CommonConstant.CAR_SEARCH_PERSON));
        map.put("carpoolSubscribe",queryCarpoolPublishInfos(carpoolPublish,CommonConstant.PERSON_SEARCH_CAR));
        return map;
    }

    private CarpoolPublish queryCarpoolPublishInfos(CarpoolPublishVo carpoolPublish, int type) {
        PageHelper.startPage(1,1, true, false); //设置分页
        Example example = new Example(CarpoolPublish.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dataStatus", CommonConstant.USEABLE_STATUS);
        criteria.andEqualTo("publishUserId",carpoolPublish.getPublishUserId());
        criteria.andEqualTo("userType",type);
        example.setOrderByClause(" createTime DESC"); ///创建时间倒叙输出
        List<CarpoolPublish> list = carpoolPublishMapper.selectByExample(example);
        PageInfo<CarpoolPublish> pageInfo = new PageInfo<>(list);
        List<CarpoolPublish> publishList = pageInfo.getList();
        return CollectionUtils.isNotEmpty(publishList) ? publishList.get(0) : null;
    }


    ///预约取消通知封装
    private TreeMap<String,TreeMap<String,String>> cancelMsgData(CarpoolPublish carpoolPublish,CarpoolOrder carpoolOrder){
        TreeMap<String,TreeMap<String,String>> params = new TreeMap<String,TreeMap<String,String>>();
        List<TreeMap<String,String>> list = new ArrayList<>();

        CarpoolUser user = new CarpoolUser();
        user.setId(carpoolOrder.getOrderUserId());
        CarpoolUser carpoolUser = carpoolUserMapper.selectOne(user);
        if (carpoolUser != null){
            list.add(WechatTemplateMsg.item(StringUtils.NullToString(carpoolUser.getNickName(),"--"), "#000000")); //昵称
            list.add(WechatTemplateMsg.item(StringUtils.NullToString(carpoolOrder.getMobile(),"--"), "#000000"));//手机号
            list.add(WechatTemplateMsg.item(DateUtils.format(carpoolOrder.getDepartureTime(),DateUtils.DATE_TIME_PATTERN), "#000000"));//日期
            list.add(WechatTemplateMsg.item(carpoolOrder.getStartPoint() +"-"+ carpoolOrder.getDestination(), "#000000"));//路线
            list.add(WechatTemplateMsg.item(StringUtils.NullToString(carpoolOrder.getCancelReason(),"对方时间有变，已取消行程，请选择其他交通工具"), "#000000"));//温馨提示
        }


        if(list.size() < 0) return  null;
        for (int i = 0 ; i < list.size() ; i++ ){
            params.put("keyword"+(i+1),list.get(i));
        }
        return params;
    }

    /**
     * 分页加排序
     * @param carpoolPublish
     * @param datas
     * @return
     */
    private  List<CarpoolPublish> pageAndSort(CarpoolPublishVo carpoolPublish ,  List<CarpoolPublish> datas){
        if(null == datas) return null;
        List<CarpoolPublish> list = new ArrayList<>();
        if( null != datas && datas.size() > 0){
            ///填充距离
            for(CarpoolPublish po : datas){
                po.setDistance(GEOUtils.calcDistance(carpoolPublish.getCustLongitude(),carpoolPublish.getCustLatitude(),po.getStartPointLongitude(),po.getStartPointLatitude()));
            }
            //根据距离排序
            Collections.sort(datas, new Comparator<CarpoolPublish>() {
                public int compare(CarpoolPublish o1, CarpoolPublish o2) {
                    Double distanceA = o1.getDistance();
                    Double distanceB = o2.getDistance();
                    // 升序
                    return distanceA.compareTo(distanceB);
                }
            });
            if (datas.size() < carpoolPublish.getLimit().intValue()){
                return  datas;
            }
            //分页
            int startIndex = 0;
            int endIndex = 0;
            if(carpoolPublish.getStart() == 0){
                startIndex = 0;
                endIndex = carpoolPublish.getLimit();
            }else  if(carpoolPublish.getStart() == 1){
                startIndex = 0;
                endIndex =  carpoolPublish.getLimit() ;
            }else{
                startIndex = (carpoolPublish.getStart() - 1) *  carpoolPublish.getLimit();
                endIndex = carpoolPublish.getStart() *  carpoolPublish.getLimit() ;
            }
            if (datas.size() > 0 && datas.size() <= carpoolPublish.getStart() *  carpoolPublish.getLimit() ) endIndex = datas.size();
            if (startIndex > datas.size())return null;//防止前端错误传递start参数
            list =  datas.subList(startIndex,endIndex);

        }
        return 	list;
    }
}
