package com.shop.listerner;

import com.shop.bean.BeanFactory;
import com.shop.connection.ConnectionPool;
import com.shop.connection.ConnectionManagerment;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;

@WebListener
public class ApplicationListener implements ServletContextListener {

    public static ConnectionManagerment connectionManagerment;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {


        ConnectionPool connectionPool = new ConnectionPool();
        connectionManagerment = new ConnectionManagerment(connectionPool);

        BeanFactory beanFactory = new BeanFactory();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
