package com.zhuxf.common.enums;

/**
 * 返回值统一编码
 * @author zhuxf
 * @date 2019-05-28 19:20:19
 */
public enum ResultEnum {

    SUCCESS(1000, "操作成功"),
    FAIL(9999, "系统未知错误"),
    LOGIN_FAIL(1001,"登陆失效，请重新登录");
    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
