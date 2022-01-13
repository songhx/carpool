package com.carpool.weixin;

/**
 * 微信认证Token
 */
public class AccessToken {

	private String  accessToken; // token
	private int  expiresIn; // 时长

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
}
