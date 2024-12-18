package com.onlineBookStore.controller;

import com.onlineBookStore.dto.CartDTO;
import com.onlineBookStore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{id}")
    public CartDTO getCartById(@PathVariable Long id) {
        return cartService.getCartById(id);
    }

    @PostMapping
    public CartDTO addToCart(@RequestBody CartDTO cartDTO) {
        return cartService.addToCart(cartDTO);
    }
}
