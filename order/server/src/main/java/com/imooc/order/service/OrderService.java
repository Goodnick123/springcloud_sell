package com.imooc.order.service;

import com.imooc.order.DTO.OrderDTO;

public interface OrderService {

    OrderDTO create(OrderDTO orderDTO);
    OrderDTO finish(String orderId);

}
