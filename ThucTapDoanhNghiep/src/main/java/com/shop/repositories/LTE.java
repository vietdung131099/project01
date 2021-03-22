package com.shop.repositories;

public class LTE<T> extends RawQuery<T> {

    public LTE(String column, Object value){
        super(column + ">= ?",value);
    }
}
