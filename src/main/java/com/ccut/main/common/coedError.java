package com.ccut.main.common;

public enum coedError {
    NOT_LOGIN(40400,"未登录",""),
    NOT_PERMISSION(40100,"没权限",""),
    PARAMS_ERROR(40200,"参数错误",""),
    PARAMS_EMPTY(40300,"参数值为空",""),
    SUCCESS(40500,"成功",""),
    SYSTEM_EX(40600,"内部异常",""),
    ;

    private  int code;
    private  String message;
    private  String describe;

    coedError(int code, String message, String describe) {
        this.code = code;
        this.message = message;
        this.describe = describe;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescribe() {
        return describe;
    }
}
