package com.onlineBookStore.mapper;

import com.onlineBookStore.dto.OrderDTO;
import com.onlineBookStore.Entity.Order;

public class OrderMapper {

    public static OrderDTO toDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setUserId(order.getUserId());
        orderDTO.setBookId(order.getBookId());
        orderDTO.setQuantity(order.getQuantity());
        orderDTO.setTotalAmount(order.getTotalAmount());
        return orderDTO;
    }

    public static Order toEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setUserId(orderDTO.getUserId());
        order.setBookId(orderDTO.getBookId());
        order.setQuantity(orderDTO.getQuantity());
        order.setTotalAmount(orderDTO.getTotalAmount());
        return order;
    }
}
