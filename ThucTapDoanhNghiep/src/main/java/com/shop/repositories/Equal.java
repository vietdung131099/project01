package com.shop.repositories;

public class Equal<T> extends RawQuery<T> {
    public Equal(String fieldName, Object value){
        super(fieldName + " = ?",value);
    }
}
