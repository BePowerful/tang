package com.wcq.tang.model;

public class Indexmsg {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column indexmsg.msg_id
     *
     * @mbg.generated Wed Mar 04 13:21:35 CST 2020
     */
    private Integer msgId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column indexmsg.msg_content
     *
     * @mbg.generated Wed Mar 04 13:21:35 CST 2020
     */
    private String msgContent;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column indexmsg.msg_id
     *
     * @return the value of indexmsg.msg_id
     *
     * @mbg.generated Wed Mar 04 13:21:35 CST 2020
     */
    public Integer getMsgId() {
        return msgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column indexmsg.msg_id
     *
     * @param msgId the value for indexmsg.msg_id
     *
     * @mbg.generated Wed Mar 04 13:21:35 CST 2020
     */
    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column indexmsg.msg_content
     *
     * @return the value of indexmsg.msg_content
     *
     * @mbg.generated Wed Mar 04 13:21:35 CST 2020
     */
    public String getMsgContent() {
        return msgContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column indexmsg.msg_content
     *
     * @param msgContent the value for indexmsg.msg_content
     *
     * @mbg.generated Wed Mar 04 13:21:35 CST 2020
     */
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent == null ? null : msgContent.trim();
    }
}