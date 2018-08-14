package com.imooc.product.impl;

import com.imooc.product.entity.ProductCategory;
import com.imooc.product.repository.ProductRepositoryApplicationTests;
import com.imooc.product.service.impl.ProductCategoryServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
public class ProductCategoryServiceImplTest extends ProductRepositoryApplicationTests{
    @Autowired
    private ProductCategoryServiceImpl productCategoryService;

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> result = productCategoryService.findByCategoryTypeIn(Arrays.asList(11,22));
        Assert.assertTrue(result.size()>0);
    }
}