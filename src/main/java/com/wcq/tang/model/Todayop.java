package com.wcq.tang.model;

import java.util.Date;

public class Todayop {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column todayop.op_id
     *
     * @mbg.generated Mon Mar 16 13:07:48 CST 2020
     */
    private Integer opId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column todayop.id
     *
     * @mbg.generated Mon Mar 16 13:07:48 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column todayop.count
     *
     * @mbg.generated Mon Mar 16 13:07:48 CST 2020
     */
    private String count;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column todayop.op_user
     *
     * @mbg.generated Mon Mar 16 13:07:48 CST 2020
     */
    private Long opUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column todayop.op_time
     *
     * @mbg.generated Mon Mar 16 13:07:48 CST 2020
     */
    private Date opTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column todayop.op_kind
     *
     * @mbg.generated Mon Mar 16 13:07:48 CST 2020
     */
    private Integer opKind;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column todayop.op_id
     *
     * @return the value of todayop.op_id
     *
     * @mbg.generated Mon Mar 16 13:07:48 CST 2020
     */
    public Integer getOpId() {
        return opId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column todayop.op_id
     *
     * @param opId the value for todayop.op_id
     *
     * @mbg.generated Mon Mar 16 13:07:48 CST 2020
     */
    public void setOpId(Integer opId) {
        this.opId = opId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column todayop.id
     *
     * @return the value of todayop.id
     *
     * @mbg.generated Mon Mar 16 13:07:48 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column todayop.id
     *
     * @param id the value for todayop.id
     *
     * @mbg.generated Mon Mar 16 13:07:48 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column todayop.count
     *
     * @return the value of todayop.count
     *
     * @mbg.generated Mon Mar 16 13:07:48 CST 2020
     */
    public String getCount() {
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column todayop.count
     *
     * @param count the value for todayop.count
     *
     * @mbg.generated Mon Mar 16 13:07:48 CST 2020
     */
    public void setCount(String count) {
        this.count = count == null ? null : count.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column todayop.op_user
     *
     * @return the value of todayop.op_user
     *
     * @mbg.generated Mon Mar 16 13:07:48 CST 2020
     */
    public Long getOpUser() {
        return opUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column todayop.op_user
     *
     * @param opUser the value for todayop.op_user
     *
     * @mbg.generated Mon Mar 16 13:07:48 CST 2020
     */
    public void setOpUser(Long opUser) {
        this.opUser = opUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column todayop.op_time
     *
     * @return the value of todayop.op_time
     *
     * @mbg.generated Mon Mar 16 13:07:48 CST 2020
     */
    public Date getOpTime() {
        return opTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column todayop.op_time
     *
     * @param opTime the value for todayop.op_time
     *
     * @mbg.generated Mon Mar 16 13:07:48 CST 2020
     */
    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column todayop.op_kind
     *
     * @return the value of todayop.op_kind
     *
     * @mbg.generated Mon Mar 16 13:07:48 CST 2020
     */
    public Integer getOpKind() {
        return opKind;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column todayop.op_kind
     *
     * @param opKind the value for todayop.op_kind
     *
     * @mbg.generated Mon Mar 16 13:07:48 CST 2020
     */
    public void setOpKind(Integer opKind) {
        this.opKind = opKind;
    }
}