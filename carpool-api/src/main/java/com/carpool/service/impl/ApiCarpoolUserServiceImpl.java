package com.carpool.service.impl;

import com.carpool.entity.CarpoolUser;
import com.carpool.service.ApiCarpoolUserService;
import com.carpool.dao.CarpoolPublishMapper;
import com.carpool.dto.CarpoolPublishVo;
import com.carpool.dto.StatCarpoolVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bjsonghongxu
 * @create 2018-02-12 18:01
 **/
@Service("apiCarpoolUserService")
public class ApiCarpoolUserServiceImpl  extends BasicSetServiceImpl<CarpoolUser> implements ApiCarpoolUserService {

    @Autowired
    private CarpoolPublishMapper carpoolPublishMapper;

    @Override
    public Map<String, Object> statCarpoolTimes(CarpoolPublishVo carpoolPublish) {
        Map<String, Object> map = new HashMap<>();
        int carpoolTimes = 0;
        int seatingTimes = 0;
        List<StatCarpoolVo> list = carpoolPublishMapper.statCarpoolTimes(carpoolPublish);
        if (CollectionUtils.isNotEmpty(list)) {
            for (StatCarpoolVo vo : list) {
                if (vo.getUserType() == 1) {
                    seatingTimes = vo.getNum();
                }else {
                    carpoolTimes = vo.getNum();
                }
            }
        }
        map.put("carpoolTimes",carpoolTimes);
        map.put("seatingTimes",seatingTimes);
        return map;
    }
}
