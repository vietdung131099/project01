package com.shop.repositories;

public class ReferenceTable { // hihi
    private String referenceTableName;
    private String conditionJoin;

    public ReferenceTable() {
    }

    public ReferenceTable(String referenceTableName, String conditionJoin) {
        this.referenceTableName = referenceTableName;
        this.conditionJoin = conditionJoin;
    }

    public String getReferenceTableName() {
        return referenceTableName;
    }

    public void setReferenceTableName(String referenceTableName) {
        this.referenceTableName = referenceTableName;
    }

    public String getConditionJoin() {
        return conditionJoin;
    }

    public void setConditionJoin(String conditionJoin) {
        this.conditionJoin = conditionJoin;
    }
}