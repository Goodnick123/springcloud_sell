package com.imooc.product.repository;

import com.imooc.product.common.ProductInfoOutput;
import com.imooc.product.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;
    @Test
    public void findByProductIdIn() {
        List<ProductInfo> list =productRepository.findByProductIdIn(Arrays.asList("157875196366160022","157875227953464068"));
        Assert.assertTrue(list.size()>0);
    }
}