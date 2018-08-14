package com.imooc.product.repository;

import com.imooc.product.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryApplicationTests {
	@Autowired
	private ProductRepository productRepository;

	@Test
	public void contextLoads() {
		List<ProductInfo> list =productRepository.findByProductStatus(0);
		Assert.assertTrue(list.size()>0);
	}


}
