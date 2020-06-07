package com.tooyi.service;

import com.tooyi.error.BusinessException;
import com.tooyi.service.model.OrderModel;

/**
 * Ceated by tooyi on 20/6/6 22:22
 */
public interface OrderService {
    //推荐使用 1. 通过前端url上传秒杀活动id，然后下单接口校验对应的id是否属于对应商品且活动已开始
    // 2. 直接在下单接口内判断对应的商品是否存在秒杀活动，若存在直接以秒杀价格下单

    OrderModel createOrder(Integer userId, Integer itemId, Integer promoId, Integer amount) throws BusinessException;

}
