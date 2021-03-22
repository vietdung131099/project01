package com.shop.repositories;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class QueryFactory {

    public static <E> Query<E> and(List<Query<E>> queries) {
        List<Query<E>> queriesFilter = queries.stream().filter(Objects::nonNull).collect(Collectors.toList());
        if(queriesFilter.isEmpty()){
            return null;
        }
        if(queriesFilter.size() == 1){
            Query<E> query = queriesFilter.get(0);
            Value value = new Value(1,query.value());
            query.addValues(Collections.singletonList(value)); // list chỉ có 1 phần tử
            return query;
        }
        return new And<>(queriesFilter);
    }
}
