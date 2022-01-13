package com.carpool.listener;


import com.carpool.utils.ResourceUtil;
import com.carpool.thread.TokenThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class GetAccessToken {
	private static final Logger logger = LoggerFactory.getLogger(GetAccessToken.class);

	private static boolean isStart = false;

	@PostConstruct
	public void listener() throws Exception {
		if (!isStart) {//这个可以解决项目启动加载两次的问题
			isStart= true;
			// 获取web.xml中配置的参数
			TokenThread.appid = ResourceUtil.getConfigByName("wx.appId");
			TokenThread.appsecret =ResourceUtil.getConfigByName("wx.secret");

			if("0".equals(ResourceUtil.getConfigByName("wx.accesstoken.open"))){
				new Thread(new TokenThread()).start();
			}
		}
	}
}
