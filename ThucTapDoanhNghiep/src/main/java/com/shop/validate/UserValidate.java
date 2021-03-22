package com.shop.validate;

import com.shop.model.request.user.UserSaveRequest;
import org.apache.commons.lang3.StringUtils;

public class UserValidate {

    public static UserSaveRequest validateObjectSave(UserSaveRequest userSaveRequest){
        return Validator.of(userSaveRequest)
                .validate(UserSaveRequest::getUserName, UserValidate::checkUserNameIsBlank, ()->new RuntimeException(""))
                .validate(UserSaveRequest::getPassword, UserValidate::checkPasswordIsBlank, ()->new RuntimeException(""))
                .get();
    }

    private static boolean checkUserNameIsBlank(String userName){
        return StringUtils.isBlank(userName);
    }

    private static boolean checkPasswordIsBlank(String password){
        return StringUtils.isBlank(password);
    }
}
