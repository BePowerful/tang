package com.wcq.tang.dto;

import java.util.Date;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/2/29 16:14
 */
public class OperationDto {
    private Integer operationId;
    private Date operationDate;
    private String operationContent;
    private Integer operationKind;

    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent;
    }

    public Integer getOperationKind() {
        return operationKind;
    }

    public void setOperationKind(Integer operationKind) {
        this.operationKind = operationKind;
    }
}
