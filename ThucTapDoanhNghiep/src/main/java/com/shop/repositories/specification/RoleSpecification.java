package com.shop.repositories.specification;

import com.shop.entity.Role;
import com.shop.repositories.Equal;
import com.shop.repositories.Join;
import com.shop.repositories.Query;

public class RoleSpecification {

    private static Query<Role> withUserId(long userId){
        if(userId == 0) return null;

        return new Join<>("permission","role.id = permission.role_id", new Equal<>("permission.user_id",userId));

    }
}