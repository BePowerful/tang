package com.wcq.tang.dto;

import lombok.Data;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/2/28 20:15
 * 后台系统消息传输对象
 */
public class SysMsgDto {
    private Long originalItems;
    private Long corpusItems;
    private Long sysUsers;
    private Long browseTimes;
    private Integer onLineUsers;

    public Long getOriginalItems() {
        return originalItems;
    }

    public void setOriginalItems(Long originalItems) {
        this.originalItems = originalItems;
    }

    public Long getCorpusItems() {
        return corpusItems;
    }

    public void setCorpusItems(Long corpusItems) {
        this.corpusItems = corpusItems;
    }

    public Long getSysUsers() {
        return sysUsers;
    }

    public void setSysUsers(Long sysUsers) {
        this.sysUsers = sysUsers;
    }

    public Long getBrowseTimes() {
        return browseTimes;
    }

    public void setBrowseTimes(Long browseTimes) {
        this.browseTimes = browseTimes;
    }

    public Integer getOnLineUsers() {
        return onLineUsers;
    }

    public void setOnLineUsers(Integer onLineUsers) {
        this.onLineUsers = onLineUsers;
    }
}
