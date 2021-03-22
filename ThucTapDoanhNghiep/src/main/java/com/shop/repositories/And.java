package com.shop.repositories;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class And<T> extends RawQuery<T> {

    public And(List<Query<T>> queries){
        if(queries.size()<2){
            throw new RuntimeException();
        }
        this.condition = queries
                .stream()
                .map(query -> query.condition())
                .collect(Collectors.joining(" AND "));

//        int i = 0;
//        for(Query query : queries){
//            Value value = new Value();
//            value.setValue(query.value());
//            value.setIndex(++i);
//            this.values.add(value);
//        }

        // dùng java8
        AtomicInteger i = new AtomicInteger(1);
        this.values = queries.stream().map(x-> new Value(i.getAndIncrement(),x.value())).collect(Collectors.toList());
    }
}
