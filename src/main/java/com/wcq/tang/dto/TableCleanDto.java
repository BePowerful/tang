package com.wcq.tang.dto;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/15 14:54
 */
public class TableCleanDto {
    private Long id;
    private String title;
    private String tags;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
    public TableCleanDto(Long id, String title,String tags) {
        this.id = id;
        this.title = title;
        this.tags = tags;
    }

    public TableCleanDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
