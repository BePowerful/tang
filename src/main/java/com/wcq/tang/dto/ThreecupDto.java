package com.wcq.tang.dto;

import java.util.Date;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/21 16:46
 */
public class ThreecupDto {
    private Long threeId;
    private String subject;
    private String predicate;
    private String object;
    private Long uploader;
    private Date buildTime;
    private String userName;
    private String strDate;

    public ThreecupDto() {
    }

    public Long getThreeId() {
        return threeId;
    }

    public void setThreeId(Long threeId) {
        this.threeId = threeId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPredicate() {
        return predicate;
    }

    public void setPredicate(String predicate) {
        this.predicate = predicate;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Long getUploader() {
        return uploader;
    }

    public void setUploader(Long uploader) {
        this.uploader = uploader;
    }

    public Date getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }
}
