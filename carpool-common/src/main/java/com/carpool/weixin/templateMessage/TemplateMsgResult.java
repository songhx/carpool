package com.carpool.weixin.templateMessage;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 模板消息返回结果
 *
 * @author bjsonghongxu
 * @create 2018-02-28 15:27
 **/
public class TemplateMsgResult implements Serializable {

    /*{
        "errcode":0,
        "errmsg":"ok",
    }*/

    private Integer errcode;
    private String errmsg;
    private String tips;

    public TemplateMsgResult() {
    }

    public TemplateMsgResult(Integer errcode, String tips) {
        this.errcode = errcode;
        this.tips = tips;
    }

    /*    返回码	说明
            40037	template_id不正确
            41028	form_id不正确，或者过期
            41029	form_id已被使用
            41030	page不正确
            45009	接口调用超过限额（目前默认每个帐号日调用限额为100万）*/
    private static  final  Map<Integer,String> statuMap = new HashMap<Integer,String>(){{
        put(0,"发送成功");
        put(40037,"template_id不正确");
        put(41028,"form_id不正确，或者过期");
        put(41029,"form_id已被使用");
        put(41030,"page不正确");
        put(45009,"接口调用超过限额");
    }};


    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {

        this.tips = (null != errcode) ? statuMap.get(errcode) : "";
    }
}
