package com.shop.repositories;

import com.shop.entity.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long>{

    List<Role> findByUserId(long userId);
}
