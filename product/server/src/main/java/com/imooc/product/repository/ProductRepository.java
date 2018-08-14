package com.imooc.product.repository;

import com.imooc.product.common.ProductInfoOutput;
import com.imooc.product.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductInfo,String>{

    List<ProductInfo> findByProductStatus(Integer producdStatus);
    List<ProductInfo> findByProductIdIn(List<String> productIdList);
}
