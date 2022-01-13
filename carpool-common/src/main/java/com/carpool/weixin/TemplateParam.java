/**
 * @Title: ReceptionPo.java
 * @Copyright (C) 2016 北京云托科技有限公司
 * @Description:
 * @Revision History:
 * @Revision 1.0 2016年6月29日  郑文婷
 */

package com.carpool.weixin;

import java.io.Serializable;

public class TemplateParam implements Serializable {
	// 参数名称
	private String name;
	// 参数值
	private String value;
	// 颜色
	private String color;

	public TemplateParam(String name, String value, String color) {
		this.name = name;
		this.value = value;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}