package com.carpool.dao;

import com.carpool.entity.CarpoolUserFormid;
import com.carpool.service.IBasicSetMapper;

/**
 * 拼车模板消息标识数据接口
 *
 * @author bjsonghongxu
 * @create 2018-02-28 11:11
 **/
public interface CarpoolUserFormidMapper extends IBasicSetMapper<CarpoolUserFormid> {
    /**
     * 移除过期的formid
     * @return
     */
    int removeExpireFormId();
}
