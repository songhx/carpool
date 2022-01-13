package com.carpool.thread;

import com.carpool.utils.WeixinUtil;
import com.carpool.weixin.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class TokenThread
 */
public class TokenThread extends HttpServlet implements Runnable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(TokenThread.class);

	private  static final  int  SLEEP_TIME = 60 * 1000 * 60 * 60 * 2; //两个小时
	// 第三方用户唯一凭证
	public static String appid = "";
	// 第三方用户唯一凭证密钥
	public static String appsecret = "";
	public static AccessToken accessToken = null;

	public void run() {
		while (true) {
			try {
				accessToken = WeixinUtil.getAccessToken(appid, appsecret);
				if (null != accessToken) {
					logger.info("获取access_token成功，有效时长{}秒 token:{}", accessToken.getExpiresIn(),accessToken.getAccessToken());
					// 休眠7000秒
					Thread.sleep((accessToken.getExpiresIn() - 200) * 1000);
				} else {
					Thread.sleep(SLEEP_TIME);
				}
			} catch (InterruptedException e) {
				try {
					Thread.sleep(60 * 1000);
				} catch (InterruptedException e1) {
					logger.error("{}", e1);
				}
				logger.error("{}", e);
			}
		}
	}}
