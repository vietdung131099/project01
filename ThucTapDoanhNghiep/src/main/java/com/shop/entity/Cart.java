package com.shop.entity;

import com.shop.constant.annotation.Column;
import com.shop.constant.annotation.Entity;
import com.shop.constant.annotation.Id;

@Entity(value = "cart")
public class Cart {
    @Id(value = "id",autoIncrement = true)
    private int id;
    @Column("product_id")
    private int productId;
    @Column("user_id")
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
