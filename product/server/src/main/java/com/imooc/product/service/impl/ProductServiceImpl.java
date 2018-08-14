package com.imooc.product.service.impl;

import com.imooc.product.common.DecreaseStockInput;
import com.imooc.product.common.ProductInfoOutput;
import com.imooc.product.entity.ProductInfo;
import com.imooc.product.enums.ProductStatusEnum;
import com.imooc.product.enums.ResultEnum;
import com.imooc.product.exception.ProductException;
import com.imooc.product.repository.ProductRepository;
import com.imooc.product.service.ProductService;
import com.imooc.product.util.JsonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;
    @Override
    public List<ProductInfo> findUpAll() {
        return productRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfoOutput> findList(List<String> productIdList) {
        return productRepository.findByProductIdIn(productIdList).stream().map(e->{
            ProductInfoOutput output = new ProductInfoOutput();
            BeanUtils.copyProperties(e,output);
            return output;
        }).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {

        List<ProductInfo> productInfoList = decreaseStockProcess(decreaseStockInputList);
        List<ProductInfoOutput> productInfoOutputList =  productInfoList.stream().map(e->{
            ProductInfoOutput productInfoOutput = new ProductInfoOutput();
            BeanUtils.copyProperties(e,productInfoOutput);
            return productInfoOutput;
        }).collect(Collectors.toList());
           //发送消息
        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutputList));

    }

    public List<ProductInfo> decreaseStockProcess(List<DecreaseStockInput> decreaseStockInputList){
        List<ProductInfo> productInfoList = new ArrayList<>();
        for(DecreaseStockInput decreaseStockInput : decreaseStockInputList){
            Optional<ProductInfo> productInfoOptional =  productRepository.findById(decreaseStockInput.getProductId());
            if(!productInfoOptional.isPresent()){
                throw  new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo  = productInfoOptional.get();
            int result = productInfo.getProductStock()-decreaseStockInput.getProductQuantity();
            if(result<0){
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            productInfo.setProductStock(result);
            productRepository.save(productInfo);
            ProductInfoOutput output = new ProductInfoOutput();
            BeanUtils.copyProperties(productInfo,output);
            productInfoList.add(productInfo);
        }
        return productInfoList;

    }
}
