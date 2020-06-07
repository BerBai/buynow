package com.tooyi.service.impl;

import com.tooyi.dao.PromoDOMapper;
import com.tooyi.dataobject.PromoDO;
import com.tooyi.service.PromoService;
import com.tooyi.service.model.PromoModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Ceated by tooyi on 20/6/7 12:20
 */
@Service
public class PromoServiceImpl implements PromoService {
    @Autowired
    private PromoDOMapper promoDOMapper;

    @Override
    @Transactional
    public PromoModel getPromoByItemId(Integer itemId) {
        // 获取秒杀活动信息
        PromoDO promoDO = promoDOMapper.selectByItemId(itemId);
        //DataObject -> Model
        PromoModel promoModel = this.convertFromDataObject(promoDO);
        if (promoModel == null) {
            return null;
        }

        // 判断秒杀活动是否即将开始，或结束
        if (promoModel.getStartDate().isAfterNow()) {
            promoModel.setStatus(1);
        } else if (promoModel.getEndDate().isBeforeNow()) {
            promoModel.setStatus(3);
        } else {
            promoModel.setStatus(2);
        }

        return promoModel;
    }

    private PromoModel convertFromDataObject(PromoDO promoDO) {
        if (promoDO == null) {
            return null;
        }
        PromoModel promoModel = new PromoModel();
        BeanUtils.copyProperties(promoDO, promoModel);
        promoModel.setPromoItemPirce(new BigDecimal(promoDO.getPromoItemPrice()));
        promoModel.setStartDate(new DateTime(promoDO.getStartDate()));
        promoModel.setEndDate(new DateTime(promoDO.getEndDate()));
        return promoModel;
    }
}
