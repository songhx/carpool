package com.carpool.vo;

import java.io.Serializable;

/**
 * 请求参数
 *
 * @author bjsonghongxu
 * @create 2017-09-22 17:22
 **/
public class RequestParameter implements Serializable {

    private String carpoolCode; // 平台业务编码

    private int needAuth; // 0  不需要权限  1 需要权限

    private String roleIds; // 角色ids

    public String getPlatformCode() {
        return carpoolCode;
    }

    public void setPlatformCode(String carpoolCode) {
        this.carpoolCode = carpoolCode;
    }

    public int getNeedAuth() {
        return needAuth;
    }

    public void setNeedAuth(int needAuth) {
        this.needAuth = needAuth;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
}
