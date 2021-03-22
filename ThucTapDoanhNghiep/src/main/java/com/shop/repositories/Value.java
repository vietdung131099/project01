package com.shop.repositories;

public class Value {
    private int index;
    private Object value;

    public Value() {
    }

    public Value(int index, Object value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}