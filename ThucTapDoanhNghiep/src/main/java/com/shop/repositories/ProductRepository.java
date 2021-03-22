package com.shop.repositories;

import com.shop.entity.Product;

import java.util.stream.Stream;

public interface ProductRepository extends JpaRepository<Product,Integer>{
    Stream<Product> findByCategoryId(int id);
}
