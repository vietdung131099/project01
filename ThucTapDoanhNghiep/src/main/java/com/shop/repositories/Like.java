package com.shop.repositories;

public class Like<T> extends RawQuery<T> {

    public Like(String column, Object value){
        super(column + " LIKE ?",value);
    }
}