package com.tooyi.error;

/**
 * EmBusinessError
 * Created by tooyi on 20/6/3 20:38
 */
public enum EmBusinessError implements CommonError {
    // 通用错误类型 10001
    PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),
    // 未知错误
    UNKNOW_ERROR(10002, "未知错误"),

    // 20000开头为用户信息相关错误定义
    USER_NOT_EXIST(20001, "用户不存在"),
    USER_LOGIN_FAIL(20002, "手机号或密码不正确"),
    USER_NOT_LOGIN(20003, "用户未登录"),

    // 30000开头为交易错误信息
    STOCK_NOT_ENOUGH(30001, "库存不足"),
    ;

    private EmBusinessError(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    private int errorCode;
    private String errorMsg;

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }

    @Override
    public CommonError setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }
}
