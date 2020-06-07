package com.tooyi.error;
/**
 * CommonError
 * Created by tooyi on 20/6/3 20:38
 */
public interface CommonError {
    public int getErrorCode();
    public String getErrorMsg();
    public CommonError setErrorMsg(String errorMsg);
}
