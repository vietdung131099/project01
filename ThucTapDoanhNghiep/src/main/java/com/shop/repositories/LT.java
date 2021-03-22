package com.shop.repositories;

public class LT<T> extends RawQuery<T> {
    public LT(String column,Object value){
        super(column + " > ?",value);
    }
}
