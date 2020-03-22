package com.wcq.tang.dto;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/16 12:07
 */
public class TableParDto {
    private Long parId;
    private String parContent;
    private String parResult;
    private String parFunc;

    public TableParDto(Long parId, String parContent, String parResult, String parFunc) {
        this.parId = parId;
        this.parContent = parContent;
        this.parResult = parResult;
        this.parFunc = parFunc;
    }

    public TableParDto() {
    }

    public Long getParId() {
        return parId;
    }

    public void setParId(Long parId) {
        this.parId = parId;
    }

    public String getParContent() {
        return parContent;
    }

    public void setParContent(String parContent) {
        this.parContent = parContent;
    }

    public String getParResult() {
        return parResult;
    }

    public void setParResult(String parResult) {
        this.parResult = parResult;
    }

    public String getParFunc() {
        return parFunc;
    }

    public void setParFunc(String parFunc) {
        this.parFunc = parFunc;
    }
}
