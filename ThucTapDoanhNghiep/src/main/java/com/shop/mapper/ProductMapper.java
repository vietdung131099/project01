package com.shop.mapper;

import com.shop.entity.Product;
import com.shop.entity.User;
import com.shop.model.request.product.ProductSaveRequest;
import com.shop.model.request.product.ProductUpdateRequest;
import com.shop.model.request.user.UserSaveRequest;
import com.shop.utils.ReflectionUtils;

public class ProductMapper {

    public static Product mapToEntity(ProductSaveRequest productSaveRequest){
        try {
            Product product = new Product();
            ReflectionUtils.copy(productSaveRequest,product);

            return product;

        } catch (IllegalAccessException e) {
            e.printStackTrace();

            return null;
        }
    }

    public static Product mapToEntity(ProductUpdateRequest productUpdateRequest){
        try {
            Product product = new Product();
            ReflectionUtils.copy(productUpdateRequest,product);

            return product;

        } catch (IllegalAccessException e) {
            e.printStackTrace();

            return null;
        }
    }
}
