package com.wcq.tang.bean.exception;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/10 14:40
 */
public enum BusinessMsgEnum {
    /** 参数异常 */
    PARMETER_EXCEPTION(102, "参数异常!"),
    /** 等待超时 */
    SERVICE_TIME_OUT(103, "服务调用超时！"),
    /** 500 : 发生异常 */
    UNEXPECTED_EXCEPTION(500, "系统发生异常，请联系管理员！");
    /**
     * 消息码
     */
    private Integer code;
    /**
     * 消息内容
     */
    private String msg;

    private BusinessMsgEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer code() {
        return code;
    }

    public String msg() {
        return msg;
    }
}
