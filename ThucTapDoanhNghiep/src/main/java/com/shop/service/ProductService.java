package com.shop.service;

import com.shop.entity.Product;
import com.shop.model.request.product.ProductDeleteRequest;
import com.shop.model.request.product.ProductSaveRequest;
import com.shop.model.request.product.ProductUpdateRequest;

public interface ProductService {
    void delete(ProductDeleteRequest productDeleteRequest);

    void save(ProductSaveRequest productSaveRequest);

    Product findProductById(int id);

    void update(ProductUpdateRequest productUpdateRequest);
}
