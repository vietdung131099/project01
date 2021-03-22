package com.shop.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManagerment {

    private ConnectionPool connectionPool;

    public ConnectionManagerment(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public Connection connection(){
        try {
            //return new ConnectionPool().getDataSource().getConnection();
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_java","root","123456789");

        } catch (Exception throwables) {
            throwables.printStackTrace();

            return null;
        }
    }
}
