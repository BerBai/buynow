package com.tooyi.service;

import com.tooyi.error.BusinessException;
import com.tooyi.service.model.ItemModel;

import java.util.List;

/**
 * Ceated by tooyi on 20/6/6 14:34
 */
public interface ItemService {
    // 创建商品
    ItemModel createItem(ItemModel itemModel) throws BusinessException;

    // 商品列表浏览
    List<ItemModel> listItem();

    // 商品详情浏览
    ItemModel getItemById(Integer id);

    // 库存扣减
    boolean decreaseStock(Integer itemId, Integer amount);

    // 商品效率增加
    void increaseSales(Integer itemId, Integer amount) throws BusinessException;
}
