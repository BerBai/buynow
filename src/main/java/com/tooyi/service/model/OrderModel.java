package com.tooyi.service.model;

import java.math.BigDecimal;

/**
 * Ceated by tooyi on 20/6/6 22:08
 */
// 用户下单的交易模型
public class OrderModel {
    // 交易号 20200606000012885
    private String id;
    // 购买的用户Id
    private Integer userId;
    // 购买的商品id
    private Integer itemId;
    //若非空，则表示以秒杀商品方式下单
    private Integer promoId;

    // 购买的数量
    private Integer amount;
    // 购买商品的单价 若promoId非空，则表示秒杀商品价格
    private BigDecimal itemPrice;

    // 购买的总价 若promoId非空，则表示秒杀商品价格
    private BigDecimal orderAmount;

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }
}
