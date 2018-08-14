package com.imooc.product.impl;

import com.imooc.product.common.DecreaseStockInput;
import com.imooc.product.common.ProductInfoOutput;
import com.imooc.product.entity.ProductInfo;
import com.imooc.product.repository.ProductRepositoryApplicationTests;
import com.imooc.product.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductServiceImplTest extends ProductRepositoryApplicationTests{
    @Autowired
    private ProductService productService;
    @Test
    public void findList() {
        List<ProductInfoOutput> result =productService.findList(Arrays.asList("157875196366160022","157875227953464068"));
        Assert.assertTrue(result.size()>0);

    }

    @Test
    public void decreaseStock(){
        DecreaseStockInput cartDTO = new DecreaseStockInput("157875196366160022",2);
        productService.decreaseStock(Arrays.asList(cartDTO));

    }
}