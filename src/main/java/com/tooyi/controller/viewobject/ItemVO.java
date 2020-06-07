package com.tooyi.controller.viewobject;

import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * Ceated by tooyi on 20/6/6 15:40
 */
public class ItemVO {
    private Integer id;
    // 商品名
    private String title;
    // 价格
    private BigDecimal price; // 使用BigDecimal，确保精度 Double存在精度问题
    // 库存
    private Integer stock;
    // 描述
    private String description;
    //销量
    private Integer sales;
    // 描述图片的url
    private String imgUrl;

    // 商品秒杀信息
    private String promoName;
    // 商品秒杀活动状态 0：没有秒杀 1：秒杀待开始 2：秒杀进行中
    private Integer promoStatus;
    // 秒杀活动id
    private Integer promoId;
    private BigDecimal promoItemPrice;
    private String startDate;
    private String endDate;

    public Integer getPromoStatus() {
        return promoStatus;
    }

    public void setPromoStatus(Integer promoStatus) {
        this.promoStatus = promoStatus;
    }

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public BigDecimal getPromoItemPrice() {
        return promoItemPrice;
    }

    public void setPromoItemPrice(BigDecimal promoItemPrice) {
        this.promoItemPrice = promoItemPrice;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
