package com.tooyi.controller;

import com.tooyi.error.BusinessException;
import com.tooyi.error.EmBusinessError;
import com.tooyi.reponse.CommonReturnType;
import com.tooyi.service.OrderService;
import com.tooyi.service.model.OrderModel;
import com.tooyi.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Ceated by tooyi on 20/6/7 08:43
 */
@RestController("order")
@RequestMapping("/order")
// allowCredentials = "true" 需要配合前端设置xhrFields授信后使得跨域session共享
@CrossOrigin(allowCredentials = "true", origins = {"*"}) // 解决前端跨域问题
public class OrderController extends BaseController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    // 封装下单请求
    @PostMapping(value = "/createorder", consumes = {CONTENT_TYPE_FORMED})
    public CommonReturnType createOrder(@RequestParam("itemId") Integer itemId,
                                        @RequestParam("amount") Integer amount,
                                        @RequestParam(name = "promoId",required = false) Integer promoId) throws BusinessException {
        // 判断用户是否登录
        Boolean isLogin = (Boolean) this.httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if (isLogin == null || !isLogin.booleanValue()) {
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN, "用户未登录");
        }
        // 获取用户的登录信息
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");

        OrderModel orderModel = orderService.createOrder(userModel.getId(), itemId, promoId,amount);

        return CommonReturnType.create(null);
    }

}
