package com.wcq.tang.dto;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/17 13:53
 */
public class TodayCollectDto {
    private Long originalId;
    private String title;
    private String tags;
    private String source;
    private String format;
    private String date;
    private String state;

    public TodayCollectDto() {
    }

    public TodayCollectDto(Long originalId, String title, String tags, String source, String format, String date, String state) {
        this.originalId = originalId;
        this.title = title;
        this.tags = tags;
        this.source = source;
        this.format = format;
        this.date = date;
        this.state = state;
    }

    public Long getOriginalId() {
        return originalId;
    }

    public void setOriginalId(Long originalId) {
        this.originalId = originalId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
