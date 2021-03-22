package com.shop.service;

import com.shop.entity.Cart;
import com.shop.model.request.cart.CartDisplayRequest;
import com.shop.model.request.cart.CartSaveRequest;

public interface CartService {

    void save(CartSaveRequest cartSaveRequest);

    void delete(CartDisplayRequest cartDisplayRequest);

    void deleteAll(int userId);

}
