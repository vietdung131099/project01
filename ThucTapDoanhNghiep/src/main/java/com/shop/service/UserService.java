package com.shop.service;

import com.shop.entity.User;
import com.shop.model.request.user.*;
import com.shop.paging.PageResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(UserSaveRequest userSaveRequest);

    Optional<User> findByUserNameAndPassword(AuthRequest authRequest);
    // thay vì trả về user có thể null hoặc không thì thằng Optional
    // trong nó sẽ khởi tạo đối tượng user và làm cho user trả về tránh bị null

    List<User> findAll();

    PageResponse<User> filterUser(UserFilterRequest filterRequest);

    void delete(UserDeleteRequest userDeleteRequest);

    void update(UserUpdateRequest userUpdateRequest);

    User findUserById(long id);

}
