package com.wcq.tang.dto;

import lombok.Data;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/5 16:31
 */
public class IntroduceDto {
    private String sysName;
    private String sysClub;
    private String sysFunc;
    private String sysYiyi;
    private String sysShuo;

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getSysClub() {
        return sysClub;
    }

    public void setSysClub(String sysClub) {
        this.sysClub = sysClub;
    }

    public String getSysFunc() {
        return sysFunc;
    }

    public void setSysFunc(String sysFunc) {
        this.sysFunc = sysFunc;
    }

    public String getSysYiyi() {
        return sysYiyi;
    }

    public void setSysYiyi(String sysYiyi) {
        this.sysYiyi = sysYiyi;
    }

    public String getSysShuo() {
        return sysShuo;
    }

    public void setSysShuo(String sysShuo) {
        this.sysShuo = sysShuo;
    }
}
