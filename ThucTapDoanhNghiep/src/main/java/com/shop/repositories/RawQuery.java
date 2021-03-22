package com.shop.repositories;

public class RawQuery<T> extends com.shop.repositories.AbstractQuery<T> {
    protected String condition;
    protected Object value;

    public RawQuery() {
    }

    public RawQuery(String condition, Object value) {
        this.condition = condition;
        this.value = value;
    }

    @Override
    public String condition() {
        return condition;
    }

    @Override
    public Object value() {
        return value;
    }
}
