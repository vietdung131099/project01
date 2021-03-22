package com.shop.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractQuery<T> implements Query<T> {
    // abstract class mà implement interface là không cần ghi đè tất cả mọi hàm của interface à ???
    // nhưng thằng nào mà extends cái abstract class đó thì phải ghi đè
    protected List<Value> values = new ArrayList<>();
    protected Map<String, ReferenceTable> reference = new HashMap<>();  // hihi


    @Override
    public List<Value> values() {
        return values;
    }

    @Override
    public void addValues(List<Value> values) {
        this.values = values;
    }

    @Override
    public Map<String,ReferenceTable> reference() { // hihi
        return reference;
    }
}
