package com.shop.mapper;

import com.shop.entity.Cart;
import com.shop.model.request.cart.CartDisplayRequest;
import com.shop.model.request.cart.CartSaveRequest;
import com.shop.utils.ReflectionUtils;

public class CartMapper {
    public static Cart mapToEntity(CartSaveRequest cartSaveRequest){
        try {

            Cart cart = new Cart();
            ReflectionUtils.copy(cartSaveRequest,cart);

            return cart;

        } catch (IllegalAccessException e) {
            e.printStackTrace();

            return null;
        }
    }

    public static Cart mapToEntity(CartDisplayRequest cartDisplayRequest){
        try {

            Cart cart = new Cart();
            ReflectionUtils.copy(cartDisplayRequest,cart);

            return cart;

        } catch (IllegalAccessException e) {
            e.printStackTrace();

            return null;
        }
    }
}
