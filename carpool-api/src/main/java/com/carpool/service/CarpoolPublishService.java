package com.carpool.service;

import com.carpool.entity.CarpoolPublish;
import com.carpool.dto.CarpoolPublishVo;

import java.util.Map;

/**
 * Created by zuimeideshiguang on 18/2/12.
 * 发布业务处理接口
 */
public interface CarpoolPublishService extends IBasicSetMapper<CarpoolPublish> {

    /**
     * 分页查询行程信息
     * @param carpoolPublish
     * @return
     */
    Map<String, Object> queryPublishListByPage(CarpoolPublishVo carpoolPublish);

    /**
     * 取消发布
     * @param carpoolPublish
     */
    void cnacelPublish(CarpoolPublish carpoolPublish);

    /**
     * 处理行程和预约单状态
     */
    void dealPublishAndOrderStatus();

    /**
     * 最近拼车信息
     * @param carpoolPublish
     * @return
     */
    Map<String, Object> queryPublishLatests(CarpoolPublishVo carpoolPublish);
}
