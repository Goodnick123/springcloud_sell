package com.imooc.order.repository;

import com.imooc.order.OrderApplication;
import com.imooc.order.OrderApplicationTests;
import com.imooc.order.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static org.junit.Assert.*;


@Component
public class OrderDetailRepositoryTest extends OrderApplicationTests{
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void testSave(){
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setDetailId("12345");
        orderDetail.setOrderId("1234567");
        orderDetail.setProductId("157875196366160022");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(0.01));
        orderDetail.setProductQuantity(1);
        OrderDetail result = orderDetailRepository.save(orderDetail);
        assertTrue(result!=null);



    }



}