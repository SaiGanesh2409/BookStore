package com.onlineBookStore.service;

import com.onlineBookStore.dto.OrderDTO;

public interface OrderService {
    OrderDTO getOrderById(Long id);
    OrderDTO createOrder(OrderDTO orderDTO);
    // Other methods...
}
