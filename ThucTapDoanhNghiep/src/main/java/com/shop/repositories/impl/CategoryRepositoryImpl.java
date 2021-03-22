package com.shop.repositories.impl;

import com.shop.constant.annotation.Repository;
import com.shop.entity.Category;
import com.shop.repositories.CategoryRepository;

@Repository
public class CategoryRepositoryImpl extends BaseRepository<Category,Integer> implements CategoryRepository {
}
