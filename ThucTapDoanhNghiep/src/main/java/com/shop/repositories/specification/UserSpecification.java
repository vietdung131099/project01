package com.shop.repositories.specification;

import com.shop.entity.User;
import com.shop.model.request.user.AuthRequest;
import com.shop.model.request.user.UserFilterRequest;
import com.shop.repositories.Equal;
import com.shop.repositories.Join;
import com.shop.repositories.Query;
import com.shop.repositories.QueryFactory;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserSpecification {

    public static Query<User> filterAuth(AuthRequest authRequest){
        List<Query<User>> queries = new ArrayList<>();
        queries.add(withUserName(authRequest.getUserName()));
        queries.add(withPassword(authRequest.getPassword()));

        return QueryFactory.and(queries);
    }

    public static Query<User> filterRequest(UserFilterRequest filter){
        List<Query<User>> queries = new LinkedList<>();
        queries.add(withPhone(filter.getPhone()));
        queries.add(withEmail(filter.getEmail()));
        queries.add(withUserName(filter.getUserName()));

        return QueryFactory.and(queries);
    }

    private static Query<User> withUserName(String userName){
        return StringUtils.isEmpty(userName) ? null : new Equal<>("user_name",userName);
    }

    private static Query<User> withPassword(String password){
        return StringUtils.isEmpty(password) ? null : new Equal<>("password",password);
    }

    private static Query<User> withPhone(String phone){
        return StringUtils.isEmpty(phone) ? null : new Equal<>("phone",phone);
    }

    private static Query<User> withEmail(String email){
        return StringUtils.isEmpty(email) ? null : new Equal<>("email",email);
    }

//    private static Query<User> withRoleName(String roleName){ // hihi
//        if(StringUtils.isEmpty(roleName)) return null;
//
//        return new Join<>("role","role.id = role_id",new Equal<>("role_name",roleName));
//    }
}
