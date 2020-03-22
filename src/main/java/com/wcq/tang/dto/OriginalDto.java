package com.wcq.tang.dto;

import com.wcq.tang.model.User;

import java.util.Date;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/4 17:49
 */
public class OriginalDto {
    private Long originalId;
    private Long uploader;
    private String title;
    private String tags;
    private Date originalDate;
    private String source;
    private String format;
    private Boolean formated;
    private Boolean cleaned;
    private String address;
    private String txtPath;
    private String cleanPath;
    private User user;


    public Long getOriginalId() {
        return originalId;
    }

    public void setOriginalId(Long originalId) {
        this.originalId = originalId;
    }

    public Long getUploader() {
        return uploader;
    }

    public void setUploader(Long uploader) {
        this.uploader = uploader;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Date getOriginalDate() {
        return originalDate;
    }

    public void setOriginalDate(Date originalDate) {
        this.originalDate = originalDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Boolean getFormated() {
        return formated;
    }

    public void setFormated(Boolean formated) {
        this.formated = formated;
    }

    public Boolean getCleaned() {
        return cleaned;
    }

    public void setCleaned(Boolean cleaned) {
        this.cleaned = cleaned;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTxtPath() {
        return txtPath;
    }

    public void setTxtPath(String txtPath) {
        this.txtPath = txtPath;
    }

    public String getCleanPath() {
        return cleanPath;
    }

    public void setCleanPath(String cleanPath) {
        this.cleanPath = cleanPath;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
