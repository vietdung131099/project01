package com.shop.service.impl;

import com.shop.constant.annotation.Autowire;
import com.shop.constant.annotation.Service;
import com.shop.entity.Category;
import com.shop.repositories.CategoryRepository;
import com.shop.service.CategoryService;

import java.util.stream.Stream;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowire
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Stream<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
}
