package com.wcq.tang.dto;

import java.util.Date;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/4 17:52
 */
public class CorpusDto extends OriginalDto {
    private Long corpusId;
    private Date corpusDate;
    private String standardContent;
    private String cixinContent;
    private String originalContent;
    private Boolean participled;
    private String username;

    public CorpusDto() {
    }

    public Long getCorpusId() {
        return corpusId;
    }

    public void setCorpusId(Long corpusId) {
        this.corpusId = corpusId;
    }

    public Date getCorpusDate() {
        return corpusDate;
    }

    public void setCorpusDate(Date corpusDate) {
        this.corpusDate = corpusDate;
    }

    public String getStandardContent() {
        return standardContent;
    }

    public void setStandardContent(String standardContent) {
        this.standardContent = standardContent;
    }

    public String getCixinContent() {
        return cixinContent;
    }

    public void setCixinContent(String cixinContent) {
        this.cixinContent = cixinContent;
    }

    public String getOriginalContent() {
        return originalContent;
    }

    public void setOriginalContent(String originalContent) {
        this.originalContent = originalContent;
    }

    public Boolean getParticipled() {
        return participled;
    }

    public void setParticipled(Boolean participled) {
        this.participled = participled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
