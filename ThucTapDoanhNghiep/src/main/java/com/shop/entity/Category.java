package com.shop.entity;

import com.shop.constant.annotation.Column;
import com.shop.constant.annotation.Entity;
import com.shop.constant.annotation.Id;

@Entity(value = "category")
public class Category {
    @Id(value = "id",autoIncrement = true)
    private int id;
    @Column(value = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
