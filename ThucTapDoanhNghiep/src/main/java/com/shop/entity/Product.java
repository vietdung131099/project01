package com.shop.entity;

import com.shop.constant.annotation.Column;
import com.shop.constant.annotation.Entity;
import com.shop.constant.annotation.Id;

@Entity(value = "product")
public class Product {
    @Id(value = "id",autoIncrement = true)
    private int id;
    @Column(value = "name")
    private String name;
    @Column(value = "description")
    private String description;
    @Column(value = "thumbnail")
    private String thumbnail;
    @Column(value = "price")
    private String price;
    @Column(value = "category_id")
    private int categoryId;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
