package com.onlineBookStore.service;

import com.onlineBookStore.dto.CartDTO;

public interface CartService {
    CartDTO getCartById(Long id);
    CartDTO addToCart(CartDTO cartDTO);
    // Other methods...
}
