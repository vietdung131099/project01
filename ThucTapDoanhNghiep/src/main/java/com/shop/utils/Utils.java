//package com.shop.utils;
//
//import com.shop.constant.Config;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.Properties;
//
//public class Utils {
////    private static final String path = "C:\\Users\\vietd\\Desktop\\clone Project\\ThucTapDoanhNghiep\\config\\databaseInfo.properties";
//    public void loadConfig(){
//        try(FileReader fileReader = new FileReader("databaseInfo.properties")) {
//            Properties properties = new Properties();
//            properties.load(fileReader);
//            Config.url = properties.getProperty("url");
//            Config.user = properties.getProperty("user");
//            Config.password = properties.getProperty("password");
//            Config.driverClassName = properties.getProperty("driverClassName");
//            Config.maxPoolSize = Integer.parseInt(properties.getProperty("maxPoolSize"));
//            Config.connectionTimeOut = Integer.parseInt(properties.getProperty("connectionTimeOut"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//}
