package com.shop.service.impl;

import com.shop.constant.annotation.Autowire;
import com.shop.constant.annotation.Service;
import com.shop.entity.Cart;
import com.shop.mapper.CartMapper;
import com.shop.model.request.cart.CartDisplayRequest;
import com.shop.model.request.cart.CartSaveRequest;
import com.shop.repositories.CartRepository;
import com.shop.service.CartService;

@Service
public class CartServiceImpl implements CartService {

    @Autowire
    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void save(CartSaveRequest cartSaveRequest) {
        Cart cart = CartMapper.mapToEntity(cartSaveRequest);
        cartRepository.save(cart);
    }

    @Override
    public void delete(CartDisplayRequest cartDisplayRequest) {
        Cart cart = CartMapper.mapToEntity(cartDisplayRequest);
        cartRepository.delete(cart.getId());
    }

    @Override
    public void deleteAll(int userId) {
        cartRepository.deleteCartByUserId(userId);
    }
}
