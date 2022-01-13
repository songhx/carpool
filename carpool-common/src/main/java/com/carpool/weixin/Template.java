package com.carpool.weixin;
import java.io.Serializable;
import java.util.List;
/**
 * @Title: ReceptionPo.java
 * @Copyright (C) 2016 北京云托科技有限公司
 * @Description:
 * @Revision History:
 * @Revision 1.0 2016年6月29日  郑文婷
 */
public class Template implements Serializable {

	// 消息接收方
	private String toUser;
	// 模板id
	private String templateId;
	// 模板消息详情链接
	private String page;
	// 消息顶部的颜色
	private String formId;
	// 参数列表
	private List<TemplateParam> templateParamList;

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String toJSON() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append(String.format("\"touser\":\"%s\"", this.toUser)).append(",");
		buffer.append(String.format("\"template_id\":\"%s\"", this.templateId)).append(",");
		buffer.append(String.format("\"page\":\"%s\"", this.page)).append(",");
		buffer.append(String.format("\"form_id\":\"%s\"", this.formId)).append(",");
		buffer.append("\"data\":{");
		TemplateParam param = null;
		for (int i = 0; i < this.templateParamList.size(); i++) {
			param = templateParamList.get(i);
			// 判断是否追加逗号
			if (i < this.templateParamList.size() - 1) {

				buffer.append(String.format("\"%s\": {\"value\":\"%s\",\"color\":\"%s\"},", param.getName(),
						param.getValue(), param.getColor()));
			} else {
				buffer.append(String.format("\"%s\": {\"value\":\"%s\",\"color\":\"%s\"}", param.getName(),
						param.getValue(), param.getColor()));
			}

		}
		buffer.append("}");
		buffer.append("}");
		return buffer.toString();
	}

	public List<TemplateParam> getTemplateParamList() {
		return templateParamList;
	}

	public void setTemplateParamList(List<TemplateParam> templateParamList) {
		this.templateParamList = templateParamList;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
}


