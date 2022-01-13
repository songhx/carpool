package com.carpool.api;

import com.carpool.annotation.IgnoreAuth;
import com.carpool.entity.AdVo;
import com.carpool.service.ApiAdService;
import com.carpool.util.ApiBaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: @author bjsonghongxu <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@RestController
@RequestMapping("/api/index")
public class ApiIndexController extends ApiBaseAction {
    @Autowired
    private ApiAdService adService;

    /**
     * app首页轮播图
     */
    @IgnoreAuth
    @RequestMapping("banner")
    public Object banner() {
        Map<String, Object> resultObj = new HashMap();
        //
        Map param = new HashMap();
        param.put("ad_position_id", 2);
        param.put("enabled", 1);

        List<AdVo> banner = adService.queryList(param);
        resultObj.put("banner", banner);
        return toResponsSuccess(resultObj);
    }
}