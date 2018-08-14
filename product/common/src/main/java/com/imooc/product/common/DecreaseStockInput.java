package com.imooc.product.common;

import lombok.Data;

/**
 * 减库存入参
 * @Author: yly
 */
@Data
public class DecreaseStockInput {

    /** 商品ID*/
    private String productId;

    /** 商品数量*/
    private Integer productQuantity;

    public DecreaseStockInput() {
    }

    public DecreaseStockInput(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}