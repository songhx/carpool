package com.carpool.service;

import com.carpool.entity.CarpoolUserFormid;

/**
 * 业务接口
 *
 * @author bjsonghongxu
 * @create 2018-02-28 11:12
 **/
public interface CarpoolUserFormidService extends IBasicSetMapper<CarpoolUserFormid> {
    int removeExpireFormId();
}
