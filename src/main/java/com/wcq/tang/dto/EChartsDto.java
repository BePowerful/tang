package com.wcq.tang.dto;


/**
 * @author wcq
 * @version 1.0
 * @date 2020/2/28 20:50
 */
public class EChartsDto {
    private String[] xData = new String[7];
    private Long[] yData = new Long[7];
    private Double[] lData = new Double[7];

    public String[] getXData() {
        return xData;
    }

    public void setXData(String[] xData) {
        this.xData = xData;
    }

    public Long[] getYData() {
        return yData;
    }

    public void setYData(Long[] yData) {
        this.yData = yData;
    }

    public Double[] getLData() {
        return lData;
    }

    public void setLData(Double[] lData) {
        this.lData = lData;
    }
}
