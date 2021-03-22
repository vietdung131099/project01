package com.shop.controller;

import com.google.gson.Gson;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

public abstract class BaseController extends HttpServlet {

    protected Gson gson;

    public BaseController(){
        this.gson = new Gson();
    }

    public <T> T mapToModel(Class<T> tClass, HttpServletRequest request){
        T t = null;
        try {
            t = tClass.newInstance();
            BeanUtils.populate(t,request.getParameterMap());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return t;
    }

    public <T> T convertJsonToModel(BufferedReader request, Class<? extends T> tClass) {
        StringBuilder json = new StringBuilder();
        String line;

        try {
            while((line = request.readLine()) != null) {
                json.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }


        return gson.fromJson(json.toString(),tClass);
    }

    public static <T> T toClass(HttpServletRequest request, Class<T> tClass) {     // hàm này thì khác gì hàm a sơn viết cho java 05 ??? hàm này thiếu trường thì vẫn map được
        StringBuilder json = new StringBuilder();
        String line;

        try {
            while ((line = request.getReader().readLine()) != null) {
                json.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Gson().fromJson(json.toString(), tClass);
    }


}
