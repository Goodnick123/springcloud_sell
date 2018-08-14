package com.imooc.product.service;

import com.imooc.product.common.DecreaseStockInput;
import com.imooc.product.common.ProductInfoOutput;
import com.imooc.product.entity.ProductInfo;

import java.util.List;

public interface ProductService {

    List<ProductInfo> findUpAll();

    List<ProductInfoOutput> findList(List<String> productIdList);

    void decreaseStock(List<DecreaseStockInput> cartDTOList);

}
