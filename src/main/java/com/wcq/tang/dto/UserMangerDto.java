package com.wcq.tang.dto;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/2/28 20:44
 */
public class UserMangerDto {
    private Long userId;
    private String userName;
    private String password;
    private String email;
    private Boolean role;
    private Boolean unuse;
    private String roleName;
    private String unuseName;
    private Long originalItems;
    private Long corpusItems;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getRole() {
        return role;
    }

    public void setRole(Boolean role) {
        this.role = role;
    }

    public Boolean getUnuse() {
        return unuse;
    }

    public void setUnuse(Boolean unuse) {
        this.unuse = unuse;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUnuseName() {
        return unuseName;
    }

    public void setUnuseName(String unuseName) {
        this.unuseName = unuseName;
    }

    public Long getOriginalItems() {
        return originalItems;
    }

    public void setOriginalItems(Long originalItems) {
        this.originalItems = originalItems;
    }

    public Long getCorpusItems() {
        return corpusItems;
    }

    public void setCorpusItems(Long corpusItems) {
        this.corpusItems = corpusItems;
    }
}
