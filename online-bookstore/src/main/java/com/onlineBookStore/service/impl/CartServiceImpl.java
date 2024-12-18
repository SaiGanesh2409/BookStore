package com.onlineBookStore.service.impl;

import com.onlineBookStore.dto.CartDTO;
import com.onlineBookStore.Entity.Cart;
import com.onlineBookStore.mapper.CartMapper;
import com.onlineBookStore.repository.CartRepository;
import com.onlineBookStore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public CartDTO getCartById(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
        return CartMapper.toDTO(cart);
    }

    @Override
    public CartDTO addToCart(CartDTO cartDTO) {
        Cart cart = CartMapper.toEntity(cartDTO);
        cart = cartRepository.save(cart);
        return CartMapper.toDTO(cart);
    }
}
