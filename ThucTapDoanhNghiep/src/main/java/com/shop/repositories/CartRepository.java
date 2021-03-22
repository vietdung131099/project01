package com.shop.repositories;

import com.shop.entity.Cart;
import com.shop.model.request.cart.CartDisplayRequest;

import java.util.stream.Stream;

public interface CartRepository extends JpaRepository<Cart,Integer>{
    Stream<CartDisplayRequest> showInCart(int userId);

    void deleteCartByUserId(int userId);
}
