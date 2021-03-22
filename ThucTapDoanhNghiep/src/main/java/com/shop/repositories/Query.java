package com.shop.repositories;

import java.util.List;
import java.util.Map;

public interface Query<T> { // đây là điều kiện condition và value

    String condition();

    Object value();

    List<Value> values();

    void addValues(List<Value> values);

    Map<String, ReferenceTable> reference(); // hihi
}

/**
 * SELECT * FROM tableName
 * if( condition == null ) => select all
 * else  =>  x WHERE condition => vậy xây dựng condition này ntn ???
 *
 *
 *
 * */