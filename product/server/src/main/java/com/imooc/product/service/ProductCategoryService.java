package com.imooc.product.service;

import com.imooc.product.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> arr);
}
