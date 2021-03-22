package com.shop.service.impl;

import com.shop.constant.annotation.Autowire;
import com.shop.constant.annotation.Service;
import com.shop.model.request.user.UserDeleteRequest;
import com.shop.repositories.PermissionRepository;
import com.shop.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowire
    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }


    @Override
    public void deleteRole(UserDeleteRequest userDeleteRequest) {
        permissionRepository.delete(userDeleteRequest.getId());
    }
}
