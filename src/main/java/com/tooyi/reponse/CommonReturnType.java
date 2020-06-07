package com.tooyi.reponse;
/**
 * CommonReturnType
 * Created by tooyi on 20/6/3 20:15
 */
public class CommonReturnType {
    // 表明对应请求的返回处理结果 “success” or “fail”
    private String status;
    // 若status返回success则data返回前端需要的json数据
    // 若status返回fail则data返回通用的错误码格式
    private Object data;

    // 定义通用的创建方法
    public static CommonReturnType create(Object result) {
        return CommonReturnType.create(result, "success");
    }

    public static CommonReturnType create(Object result, String status) {
        CommonReturnType type = new CommonReturnType();
        type.setStatus(status);
        type.setData(result);
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
