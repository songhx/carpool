package com.carpool.weixin.templateMessage;

import com.alibaba.fastjson.JSONObject;
import com.carpool.utils.RequestUtil;
import com.carpool.weixin.WeixinConfig;
import org.apache.commons.lang.StringUtils;

/**
 * 发送模板消息
 *
 * @author bjsonghongxu
 * @create 2018-02-28 14:56
 **/
public class WechatTemplateMsgUtil {


    /**
     * 发送模板消息
     * @param accessToken
     * @param data
     * @return 状态
     */
    public static TemplateMsgResult sendTemplateMsg(String accessToken, String data) {
        if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(data)){
            return new TemplateMsgResult(400,"参数传递错误");
        }
        String json = RequestUtil.wxHttpsRequest(WeixinConfig.TEMPLATE_MESSAGE_SEND + accessToken, RequestUtil.POST,data);
        return StringUtils.isNotBlank(json) ? JSONObject.parseObject(json ,TemplateMsgResult.class) : null;
    }


    /**
     * 获取模板消息
     * @param msg
     * @return
     */
    public  static String createTemplateMsgJson(WechatTemplateMsg msg){
        return JSONObject.toJSONString(msg);
    }
}
