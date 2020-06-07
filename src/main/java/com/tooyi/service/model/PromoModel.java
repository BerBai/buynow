package com.tooyi.service.model;

import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * Ceated by tooyi on 20/6/7 12:06
 */
public class PromoModel {
    // 秒杀活动状态 1：未开始 2：进行中 3：已结束
    private Integer status;

    private Integer id;
    // 活动名称
    private String promoName;
    // 秒杀开始时间
    private DateTime startDate;
    // 秒杀结束时间
    private DateTime endDate;
    // 秒杀活动的适用商品
    private Integer itemId;
    // 秒杀活动的商品价格
    private BigDecimal promoItemPirce;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getPromoItemPirce() {
        return promoItemPirce;
    }

    public void setPromoItemPirce(BigDecimal promoItemPirce) {
        this.promoItemPirce = promoItemPirce;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
