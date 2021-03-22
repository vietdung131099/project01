package com.shop.repositories;

import com.shop.paging.PageAble;
import com.shop.paging.PageRequest;
import com.shop.paging.PageResponse;

import java.util.Optional;
import java.util.stream.Stream;

public interface JpaRepository<T,ID> { // cái T tương ứng với entity còn ID tương ứng với ID của class đó
    T save(T t);

    void update(ID id, T t);

    Optional<T> findById(ID id); // optional java8 tránh việc nullpointerException

    void delete(ID id);

    Stream<T> findAll();

    Stream<T> findAll(PageAble pageAble);

    long count();

    Stream<T> findAll(Query<T> query);
//    Stream<T> findByCondition(Map<String, Object> condition);

    PageResponse<T> findAll(Query<T> query, PageRequest pageRequest);

    long count(Query<T> query);

    Optional<T> findOne(Query<T> query);
}

