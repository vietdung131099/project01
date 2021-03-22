package com.shop.service.impl;

import com.shop.constant.annotation.Autowire;
import com.shop.constant.annotation.Service;
import com.shop.entity.Product;
import com.shop.mapper.ProductMapper;
import com.shop.model.request.product.ProductDeleteRequest;
import com.shop.model.request.product.ProductSaveRequest;
import com.shop.model.request.product.ProductUpdateRequest;
import com.shop.repositories.ProductRepository;
import com.shop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowire
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void delete(ProductDeleteRequest productDeleteRequest) {
        productRepository.delete(productDeleteRequest.getId());
    }

    @Override
    public void save(ProductSaveRequest productSaveRequest) {
        Product product = ProductMapper.mapToEntity(productSaveRequest);
        productRepository.save(product);
    }

    @Override
    public Product findProductById(int id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void update(ProductUpdateRequest productUpdateRequest) {
        Product product = ProductMapper.mapToEntity(productUpdateRequest);
        productRepository.update(product.getId(),product);
    }
}
