package com.shop.repositories.impl;

import com.shop.constant.annotation.Repository;
import com.shop.entity.Cart;
import com.shop.listerner.ApplicationListener;
import com.shop.model.request.cart.CartDisplayRequest;
import com.shop.repositories.CartRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.shop.utils.ReflectionUtils.convertToEntity;

@Repository
public class CartRepositoryImpl extends BaseRepository<Cart,Integer> implements CartRepository {
    @Override
    public Stream<CartDisplayRequest> showInCart(int userId) {
        try(Connection connection = ApplicationListener.connectionManagerment.connection()) {
            String sql = "SELECT cart.id, thumbnail , name , price , product_id, user_id FROM product JOIN cart ON cart.product_id = product.id WHERE user_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,userId);
            ResultSet rs = ps.executeQuery();
            List<CartDisplayRequest> list = new ArrayList<>();
            while(rs.next()){
                CartDisplayRequest cartDisplayRequest = new CartDisplayRequest();
                cartDisplayRequest.setId(rs.getInt(1));
                cartDisplayRequest.setThumbnail(rs.getString(2));
                cartDisplayRequest.setName(rs.getString(3));
                cartDisplayRequest.setPrice(rs.getString(4));
                cartDisplayRequest.setProductId(rs.getInt(5));
                cartDisplayRequest.setUserId(rs.getInt(6));
                list.add(cartDisplayRequest);
            }

            return list.stream();

        } catch (SQLException throwables) {
            throwables.printStackTrace();

            return Stream.of();
        }
    }

    @Override
    public void deleteCartByUserId(int userId) {
        try(Connection connection = ApplicationListener.connectionManagerment.connection()) {
            String sql = "DELETE FROM cart WHERE user_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,userId);
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
