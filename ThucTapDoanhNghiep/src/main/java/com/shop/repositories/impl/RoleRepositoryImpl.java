package com.shop.repositories.impl;

import com.shop.constant.annotation.Repository;
import com.shop.entity.Role;
import com.shop.listerner.ApplicationListener;
import com.shop.repositories.RoleRepository;
import com.shop.utils.ReflectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class RoleRepositoryImpl extends BaseRepository<Role,Long> implements RoleRepository {
    @Override
    public List<Role> findByUserId(long userId) {
        String sql = "SELECT role.* " +
                "FROM role " +
                "JOIN permission p on role.id = p.role_id " +
                "WHERE p.user_id = ?";
        List<Role> roles = new LinkedList<>();
        try(Connection connection = ApplicationListener.connectionManagerment.connection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1,userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Role role = ReflectionUtils.convertToEntity(rs,Role.class);
                roles.add(role);
            }
            return roles;

        }catch (Exception e){
            return roles;
        }
    }
}
