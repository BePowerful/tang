package com.wcq.tang.dto;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/15 14:52
 */
//dat1:{"select":"1,2","cleanId":"","cleanContent":"我爱你","cleanResult":""}
public class CleanDto {
    private String  select;
    private Long cleanId;
    private String cleanContent;
    private String cleanResult;

    public CleanDto(String select, Long cleanId, String cleanContent, String cleanResult) {
        this.select = select;
        this.cleanId = cleanId;
        this.cleanContent = cleanContent;
        this.cleanResult = cleanResult;
    }

    public CleanDto() {
        this.select = "1,2";
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public Long getCleanId() {
        return cleanId;
    }

    public void setCleanId(Long cleanId) {
        this.cleanId = cleanId;
    }

    public String getCleanContent() {
        return cleanContent;
    }

    public void setCleanContent(String cleanContent) {
        this.cleanContent = cleanContent;
    }

    public String getCleanResult() {
        return cleanResult;
    }

    public void setCleanResult(String cleanResult) {
        this.cleanResult = cleanResult;
    }
}
