package com.shop.service.impl;

import com.shop.constant.annotation.Autowire;
import com.shop.constant.annotation.Service;
import com.shop.entity.Role;
import com.shop.repositories.RoleRepository;
import com.shop.service.RoleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowire
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getRoleByUserId(long userId) {
        return roleRepository.findByUserId(userId);
    }

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll().collect(Collectors.toList());
    }
}
