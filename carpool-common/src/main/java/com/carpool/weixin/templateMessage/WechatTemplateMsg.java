package com.carpool.weixin.templateMessage;

import java.io.Serializable;
import java.util.TreeMap;

/**
 * 模板消息实体
 *
 * @author bjsonghongxu
 * @create 2018-02-28 14:41
 **/
public class WechatTemplateMsg implements Serializable {

 /*    参数	  必填	说明
    touser	  是	    接收者（用户）的 openid
    template_id	是	所需下发的模板消息的id
    page	  否	    点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转。
    form_id	  是 	表单提交场景下，为 submit 事件带上的 formId；支付场景下，为本次支付的 prepay_id
    data	是	    模板内容，不填则下发空模板
    color	否	    模板内容字体的颜色，不填默认黑色
    emphasis_keyword	否	模板需要放大的关键词，不填则默认无放大*/

    private String touser; //接收者openid

    private String template_id; //模板ID

    private String page; //模板跳转链接

    private String form_id;

    private TreeMap<String, TreeMap<String, String>> data; //data数据

    private String color;

    private String emphasis_keyword; // 模板需要放大的关键词，不填则默认无放大

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public TreeMap<String, TreeMap<String, String>> getData() {
        return data;
    }

    public void setData(TreeMap<String, TreeMap<String, String>> data) {
        this.data = data;
    }

    public String getForm_id() {
        return form_id;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEmphasis_keyword() {
        return emphasis_keyword;
    }

    public void setEmphasis_keyword(String emphasis_keyword) {
        this.emphasis_keyword = emphasis_keyword;
    }

    /**
     * 参数
     * @param value
     * @param color 可不填
     * @return
     */
    public static TreeMap<String, String> item(String value, String color) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("value", value);
        params.put("color", color);
        return params;
    }
}
