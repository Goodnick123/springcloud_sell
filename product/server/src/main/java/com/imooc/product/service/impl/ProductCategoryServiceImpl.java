package com.imooc.product.service.impl;

import com.imooc.product.entity.ProductCategory;
import com.imooc.product.repository.ProductCategoryRepository;
import com.imooc.product.service.ProductCategoryService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> arr) {
        return productCategoryRepository.findByCategoryTypeIn(arr);
    }
}
