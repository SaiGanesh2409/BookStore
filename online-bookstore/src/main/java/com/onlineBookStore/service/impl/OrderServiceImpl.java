package com.onlineBookStore.service.impl;

import com.onlineBookStore.dto.OrderDTO;
import com.onlineBookStore.Entity.Order;
import com.onlineBookStore.mapper.OrderMapper;
import com.onlineBookStore.repository.OrderRepository;
import com.onlineBookStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return OrderMapper.toDTO(order);
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = OrderMapper.toEntity(orderDTO);
        order = orderRepository.save(order);
        return OrderMapper.toDTO(order);
    }
}
