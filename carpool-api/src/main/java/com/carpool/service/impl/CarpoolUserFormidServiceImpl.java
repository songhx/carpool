package com.carpool.service.impl;

import com.carpool.entity.CarpoolUserFormid;
import com.carpool.dao.CarpoolUserFormidMapper;
import com.carpool.service.CarpoolUserFormidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bjsonghongxu
 * @create 2018-02-28 11:13
 **/
@Service
public class CarpoolUserFormidServiceImpl extends BasicSetServiceImpl<CarpoolUserFormid> implements CarpoolUserFormidService {

    @Autowired
    private CarpoolUserFormidMapper carpoolUserFormidMapper;
    @Override
    public int removeExpireFormId() {
        return carpoolUserFormidMapper.removeExpireFormId();
    }
}
