package com.shop.repositories.impl;

import com.shop.constant.annotation.Id;
import com.shop.constant.annotation.Repository;
import com.shop.listerner.ApplicationListener;
import com.shop.repositories.PermissionRepository;


import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.shop.utils.ReflectionUtils.primaryName;

@Repository
public class PermissionRepositoryImpl implements PermissionRepository {
    @Override
    public void save(List<Long> roleIds, long userId) {
        String sql = "INSERT INTO permission(user_id, role_id) VALUES%s";
        StringBuilder condition = new StringBuilder();
        for(int i=0;i< roleIds.size();i++){
            condition.append("(").append(userId).append(",").append(roleIds.get(i)).append("),");
        }

        sql = String.format(sql,condition.deleteCharAt(condition.length() - 1));


        try(Connection connection = ApplicationListener.connectionManagerment.connection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {

        String sql = "DELETE FROM permission WHERE user_id = ?";
        Connection connection = ApplicationListener.connectionManagerment.connection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1,id);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
