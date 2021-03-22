package com.shop.repositories.impl;

import com.shop.constant.annotation.Repository;
import com.shop.entity.Product;
import com.shop.listerner.ApplicationListener;
import com.shop.repositories.ProductRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.shop.utils.ReflectionUtils.convertToEntity;

@Repository
public class ProductRepositoryImpl extends BaseRepository<Product,Integer> implements ProductRepository {
    @Override
    public Stream<Product> findByCategoryId(int id) {
        try(Connection connection = ApplicationListener.connectionManagerment.connection()) {
            String sql = "SELECT * FROM product WHERE category_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            List<Product> list = new ArrayList<>();
            while(rs.next()){
                Product product = convertToEntity(rs,Product.class);
                list.add(product);
            }

            return list.stream();

        } catch (SQLException throwables) {
            throwables.printStackTrace();

            return Stream.of();
        }
    }

}
