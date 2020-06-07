package com.tooyi.service;

import com.tooyi.service.model.PromoModel;

/**
 * Ceated by tooyi on 20/6/7 12:18
 */
public interface PromoService {
    // 根据itemid 获取即将进行 or 开始的秒杀活动
    PromoModel getPromoByItemId(Integer itemId);
}
