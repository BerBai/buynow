package com.tooyi.service;

import com.tooyi.error.BusinessException;
import com.tooyi.service.model.UserModel;

public interface UserService {
    // 通过用户id获取用户对象的方法
    UserModel getUserById(Integer id);

    // 用戶注冊
    void register(UserModel userModel) throws BusinessException;

    /**
     * 用户登录
     * Created by tooyi on 20/6/4 19:30
     * telphone 用户注册手机号
     * password 加密后的密码
     */

    UserModel login(String telphone, String encrptPassword) throws BusinessException;

}
