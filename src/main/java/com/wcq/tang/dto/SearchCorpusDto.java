package com.wcq.tang.dto;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/17 21:32
 */
public class SearchCorpusDto {
    private Long corpusId;
    private String originalContent;
    private String cixinContent;
    private String username;
    private String corpusDate;
    private String tags;
    private String source;

    public Long getCorpusId() {
        return corpusId;
    }

    public void setCorpusId(Long corpusId) {
        this.corpusId = corpusId;
    }

    public String getOriginalContent() {
        return originalContent;
    }

    public void setOriginalContent(String originalContent) {
        this.originalContent = originalContent;
    }

    public String getCixinContent() {
        return cixinContent;
    }

    public void setCixinContent(String cixinContent) {
        this.cixinContent = cixinContent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCorpusDate() {
        return corpusDate;
    }

    public void setCorpusDate(String corpusDate) {
        this.corpusDate = corpusDate;
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
}
