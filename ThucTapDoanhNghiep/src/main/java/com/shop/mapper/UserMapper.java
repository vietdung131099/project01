package com.shop.mapper;

import com.shop.constant.annotation.Component;
import com.shop.entity.User;
import com.shop.model.request.user.UserDeleteRequest;
import com.shop.model.request.user.UserSaveRequest;
import com.shop.model.request.user.UserUpdateRequest;
import com.shop.utils.ReflectionUtils;

@Component
public class UserMapper {

    public static User mapToEntity(UserSaveRequest userSaveRequest){
        try {
            User user = new User();
            ReflectionUtils.copy(userSaveRequest,user);

            return user;

        } catch (IllegalAccessException e) {
            e.printStackTrace();

            return null;
        }
    }

    public static User mapToEntity(UserDeleteRequest userDeleteRequest){
        try {
            User user = new User();
            ReflectionUtils.copy(userDeleteRequest,user);

            return user;

        } catch (IllegalAccessException e) {
            e.printStackTrace();

            return null;
        }
    }

    public static User mapToEntity(UserUpdateRequest userUpdateRequest){
        try {
            User user = new User();
            ReflectionUtils.copy(userUpdateRequest,user);

            return user;

        } catch (IllegalAccessException e) {
            e.printStackTrace();

            return null;
        }
    }
}
