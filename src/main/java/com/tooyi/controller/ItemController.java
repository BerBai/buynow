package com.tooyi.controller;

import com.tooyi.controller.viewobject.ItemVO;
import com.tooyi.error.BusinessException;
import com.tooyi.reponse.CommonReturnType;
import com.tooyi.service.ItemService;
import com.tooyi.service.model.ItemModel;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Ceated by tooyi on 20/6/6 15:38
 */
@RestController("/item")
@RequestMapping("/item")
// allowCredentials = "true" 需要配合前端设置xhrFields授信后使得跨域session共享
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*") // 解决前端跨域问题
public class ItemController extends BaseController {

    @Autowired
    private ItemService itemService;

    // 创建商品
    @RequestMapping(value = "/create", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    public CommonReturnType createItem(@RequestParam("title") String title,
                                       @RequestParam("price") BigDecimal price,
                                       @RequestParam("description") String description,
                                       @RequestParam("imgUrl") String imgUrl,
                                       @RequestParam("stock") Integer stock) throws BusinessException {
        // 封装service请求用来创建商品
        ItemModel itemModel = new ItemModel();
        itemModel.setTitle(title);
        itemModel.setPrice(price);
        itemModel.setStock(stock);
        itemModel.setDescription(description);
        itemModel.setImgUrl(imgUrl);

        ItemModel itemModelForReturn = itemService.createItem(itemModel);

        ItemVO itemVO = this.convertFromModel(itemModelForReturn);
        return CommonReturnType.create(itemVO);
    }

    // 商品详情浏览
    @GetMapping(value = "/get")
    public CommonReturnType getItem(@RequestParam("id") Integer id) {
        ItemModel itemModel = itemService.getItemById(id);
        ItemVO itemVO = convertFromModel(itemModel);
        return CommonReturnType.create(itemVO);
    }

    // 商品列表页面浏览
    @GetMapping(value = "/getlist")
    public CommonReturnType getItemList() {
        List<ItemModel> itemModelList = itemService.listItem();

        // 使用stream api将list中的itemDomel转化为itemVO
        List<ItemVO> itemVOList = itemModelList.stream().map(itemModel -> {
            ItemVO itemVO = this.convertFromModel(itemModel);
            return itemVO;
        }).collect(Collectors.toList());

        return CommonReturnType.create(itemVOList);
    }


    private ItemVO convertFromModel(ItemModel itemModel) {
        if(itemModel ==null) {
            return null;
        }
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel,itemVO);
        if(itemModel.getPromoModel() != null) {
            // 有正在进行或即将进行的秒杀活动
            itemVO.setPromoId(itemModel.getPromoModel().getId());
//            itemVO.setEndDate(itemModel.getPromoModel().getEndDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
            itemVO.setPromoItemPrice(itemModel.getPromoModel().getPromoItemPirce());
            itemVO.setStartDate(itemModel.getPromoModel().getStartDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
            itemVO.setPromoStatus(itemModel.getPromoModel().getStatus());
        } else {
            itemVO.setPromoStatus(0);
        }

        return itemVO;
    }
}
