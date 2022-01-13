package com.carpool.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 拼车导航
 *
 * @author bjsonghongxu
 * @create 2019-04-24 13:42
 **/
@Table(name = "carpool_nav")
public class CarpoolNav implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id; // '自增id',

    private String name;
    private String icon;
    private String disabledIcon;
    private String tip;
    private Integer open;
    private Integer needLogin;
    private String skipTo;
    private Integer dataStatus; // 状态

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDisabledIcon() {
        return disabledIcon;
    }

    public void setDisabledIcon(String disabledIcon) {
        this.disabledIcon = disabledIcon;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }

    public Integer getNeedLogin() {
        return needLogin;
    }

    public void setNeedLogin(Integer needLogin) {
        this.needLogin = needLogin;
    }

    public String getSkipTo() {
        return skipTo;
    }

    public void setSkipTo(String skipTo) {
        this.skipTo = skipTo;
    }

    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }
}
