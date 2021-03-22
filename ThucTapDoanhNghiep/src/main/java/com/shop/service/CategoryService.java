package com.shop.service;

import com.shop.entity.Category;

import java.util.stream.Stream;

public interface CategoryService {
    Stream<Category> getAllCategory();
}
