package com.onlineBookStore.mapper;

import com.onlineBookStore.dto.CartDTO;
import com.onlineBookStore.Entity.Cart;

public class CartMapper {

    public static CartDTO toDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setUserId(cart.getUserId());
        cartDTO.setBookId(cart.getBookId());
        cartDTO.setQuantity(cart.getQuantity());
        return cartDTO;
    }

    public static Cart toEntity(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setId(cartDTO.getId());
        cart.setUserId(cartDTO.getUserId());
        cart.setBookId(cartDTO.getBookId());
        cart.setQuantity(cartDTO.getQuantity());
        return cart;
    }
}
