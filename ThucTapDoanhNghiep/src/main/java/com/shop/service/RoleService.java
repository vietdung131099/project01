package com.shop.service;

import com.shop.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRoleByUserId(long userId);

    List<Role> getAllRole();
}
