package com.shop.connection;

import com.shop.constant.Config;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionPool {

    private volatile HikariConfig config;
    private volatile HikariDataSource dataSource;

    public ConnectionPool(){
        this.config = new HikariConfig();
        this.config.setJdbcUrl("jdbc:mysql://localhost:3306/shop_java");
        this.config.setUsername("root");
        this.config.setPassword("123456789");
        this.config.setDriverClassName("com.mysql.jdbc.Driver");
        this.config.setMaximumPoolSize(15);
        this.config.setConnectionTimeout(3*1000);
        this.dataSource = new HikariDataSource(this.config);
    }

    public HikariDataSource getDataSource() {
        return dataSource;
    }
}
