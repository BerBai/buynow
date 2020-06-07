package com.tooyi.service.impl;

import com.tooyi.dao.ItemDOMapper;
import com.tooyi.dao.ItemStockDOMapper;
import com.tooyi.dataobject.ItemDO;
import com.tooyi.dataobject.ItemStockDO;
import com.tooyi.error.BusinessException;
import com.tooyi.error.EmBusinessError;
import com.tooyi.service.ItemService;
import com.tooyi.service.PromoService;
import com.tooyi.service.model.ItemModel;
import com.tooyi.service.model.PromoModel;
import com.tooyi.validator.ValidationResult;
import com.tooyi.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Ceated by tooyi on 20/6/6 14:44
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private ItemDOMapper itemDOMapper;

    @Autowired
    private ItemStockDOMapper itemStockDOMapper;

    @Autowired
    private PromoService promoService;

    private ItemDO convertFromModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemDO itemDO = new ItemDO();
        BeanUtils.copyProperties(itemModel, itemDO);
        itemDO.setPrice(itemModel.getPrice().doubleValue());
//        itemDO.setImgUrl();
        return itemDO;
    }

    private ItemStockDO convertStockFromModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemStockDO itemStockDO = new ItemStockDO();
        itemStockDO.setStock(itemModel.getStock());
        itemStockDO.setItemId(itemModel.getId());
        return itemStockDO;
    }

    @Override
    @Transactional
    public ItemModel createItem(ItemModel itemModel) throws BusinessException {
        // 校验入参
        ValidationResult validationResult = validator.validate(itemModel);
        if (validationResult.isHasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, validationResult.getErrMsg());
        }

        //转化itemmodel->dataobject
        ItemDO itemDO = this.convertFromModel(itemModel);

        // 写入数据库
        itemDOMapper.insertSelective(itemDO);
        itemModel.setId(itemDO.getId()); // 获取商品表id

        ItemStockDO itemStockDO = this.convertStockFromModel(itemModel);
        itemStockDOMapper.insertSelective(itemStockDO);

        // 返回创建完成的对象
        return this.getItemById(itemDO.getId());
    }

    @Override
    public List<ItemModel> listItem() {
        // 调用ItemDOMapper获取列表
        List<ItemDO> itemDOList = itemDOMapper.listItem();
        // 使用Java8的map api转化itemDO->itemModel
        List<ItemModel> itemModelList = itemDOList.stream().map(itemDO -> {
            ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());
            ItemModel itemModel = convertFromDataObject(itemDO,itemStockDO);
            return itemModel;
        }).collect(Collectors.toList());
        return itemModelList;
    }

    @Override
    public ItemModel getItemById(Integer id) {
        // 调用ItemDOMapper获取对应的商品信息
        ItemDO itemDO = itemDOMapper.selectByPrimaryKey(id);

        if (itemDO == null) {
            return null;
        }
        // 通过商品id获取库存
        ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());

        // 将dataobject->model
        ItemModel itemModel = this.convertFromDataObject(itemDO,itemStockDO);

        // 获取活动商品信息
        PromoModel promoModel = promoService.getPromoByItemId(itemDO.getId());
        if (promoModel != null && promoModel.getStatus().intValue() != 3) {
            itemModel.setPromoModel(promoModel);
        }

        return itemModel;
    }

    @Override
    @Transactional
    public boolean decreaseStock(Integer itemId, Integer amount) {
        // 返回影响行数
        int affectedRow = itemStockDOMapper.decreaseStock(itemId,amount);
        if(affectedRow > 0) {
            // 更新库存成功
            return true;
        }
        // 更新库存失败
        return false;
    }

    @Override
    @Transactional
    public void increaseSales(Integer itemId, Integer amount) throws BusinessException {
        itemDOMapper.increaseSales(itemId,amount);
    }

    private ItemModel convertFromDataObject(ItemDO itemDO, ItemStockDO itemStockDO) {
        if (itemDO == null) {
            return null;
        }
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDO,itemModel);
        itemModel.setPrice(new BigDecimal(itemDO.getPrice()));
        itemModel.setStock(itemStockDO.getStock());
//        itemModel.setSales(0);
        return itemModel;
    }
}
