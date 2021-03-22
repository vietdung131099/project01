package com.shop.entity;

import com.shop.constant.annotation.Column;
import com.shop.constant.annotation.Entity;
import com.shop.constant.annotation.Id;

import java.io.Serializable;

@Entity(value = "user")
public class User {
    @Id(value = "id" , autoIncrement = true)
    private Integer id;
    @Column(value = "user_name")
    private String userName;
    @Column(value = "password")
    private String password;
    @Column(value = "phone")
    private String phone;
    @Column(value = "email")
    private String email;
    @Column(value = "name")
    private String name;
    @Column(value = "address")
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
