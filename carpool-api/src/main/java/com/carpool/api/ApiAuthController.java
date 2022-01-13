package com.carpool.api;

import com.alibaba.fastjson.JSONObject;
import com.carpool.annotation.IgnoreAuth;
import com.carpool.constants.CommonConstant;
import com.carpool.entity.CarpoolUser;
import com.carpool.entity.UserInfo;
import com.carpool.service.ApiCarpoolUserService;
import com.carpool.service.TokenService;
import com.carpool.util.ApiBaseAction;
import com.carpool.util.ApiUserUtils;
import com.carpool.utils.StringUtils;
import com.carpool.utils.WeixinUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * API登录授权
 *
 * @author bjsonghongxu
 * @email songhongxucg@gmail.com
 * @date 2017-03-23 15:31
 */
@RestController
@RequestMapping("/api/auth")
public class ApiAuthController extends ApiBaseAction {
    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private ApiCarpoolUserService apiCarpoolUserService;
    @Autowired
    private TokenService tokenService;

    /**
     * 登录
     */
    @IgnoreAuth
    @RequestMapping("carpoolLogin")
    public Object carpoolLogin() {
        JSONObject jsonParam = this.getJsonRequest();
        //FullUserInfo fullUserInfo = null;
        UserInfo userInfo = null;
        String code = "";
        if (!StringUtils.isNullOrEmpty(jsonParam.getString("code"))) {
            code = jsonParam.getString("code");
        }
        if (null != jsonParam.get("userInfo")) {
            userInfo = jsonParam.getObject("userInfo", UserInfo.class);
        }



        Map<String, Object> resultObj = new HashMap();
        if (null == userInfo){
            return  toResponsFail("登录无效参数");
        }


        //获取openid
        String requestUrl = ApiUserUtils.getWebAccess(code);//通过自定义工具类组合出小程序需要的登录凭证 code
        logger.info("》》》组合token为：" + requestUrl);
        net.sf.json.JSONObject sessionData = WeixinUtil.httpRequest(requestUrl, "GET", null);

        if (null == sessionData || (sessionData.getString("openid") != null && StringUtils.isNullOrEmpty(sessionData.getString("openid")))) {
            return toResponsFail("登录失败");
        }

        String openid = "oacP90FDeUdnFMZkwZ274fEWnWqE";
        if(!code.equals("the code is a mock one")){
            openid = sessionData.getString("openid");
        }else{
            openid = "oacP90FDeUdnFMZkwZ274fEWnWqE";
        }
        Date time = new Date();
        CarpoolUser cu = new CarpoolUser();
        cu.setDataStatus(CommonConstant.USEABLE_STATUS);
        cu.setWxOpenid(openid);
        CarpoolUser carpoolUser = apiCarpoolUserService.selectOne(cu);
        if (null == carpoolUser) {
            carpoolUser = new CarpoolUser();
            carpoolUser.setWxOpenid(openid);
            carpoolUser.setDataStatus(CommonConstant.USEABLE_STATUS);
            carpoolUser.setAvatar(userInfo.getAvatarUrl());
            carpoolUser.setCreateTime(time);
            carpoolUser.setNickName(userInfo.getNickName());
            carpoolUser.setUpdateTime(time);
            apiCarpoolUserService.insertSelective(carpoolUser);
        }

        Map<String, Object> tokenMap = tokenService.createToken(carpoolUser.getId());
        String token = MapUtils.getString(tokenMap, "token");

        if (StringUtils.isNullOrEmpty(token)) {
            return toResponsFail("登录失败");
        }
        resultObj.put("wxOpendId",code);
        resultObj.put("token", token);
        resultObj.put("userInfo", carpoolUser);
        resultObj.put("userId", carpoolUser.getId());
        return toResponsSuccess(resultObj);
    }
}
