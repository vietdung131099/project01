package com.shop.repositories;


public class Join<T, X> extends RawQuery<T> { // hihi
    public Join(String referenceTable, String conditionJoin, RawQuery<T> condition){
        super(condition.condition, condition.value);
        if(!this.reference.containsKey(referenceTable)){ // cái referenceTable check điều kiện cái thằng điều kiện join tồn tại rồi thì không join 2 lần ???
            reference.put(referenceTable,new ReferenceTable(referenceTable,conditionJoin));
        }
    }
}
