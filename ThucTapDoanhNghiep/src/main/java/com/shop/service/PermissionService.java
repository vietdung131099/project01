package com.shop.service;

import com.shop.model.request.user.UserDeleteRequest;

public interface PermissionService {
    void deleteRole(UserDeleteRequest userDeleteRequest);
}
