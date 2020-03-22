package com.wcq.tang.dto;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/16 15:43
 */
public class CixinDto {
    private Integer id;
    private String en;
    private String ch;

    public CixinDto(Integer id, String en, String ch) {
        this.id = id;
        this.en = en;
        this.ch = ch;
    }

    public CixinDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }
}
