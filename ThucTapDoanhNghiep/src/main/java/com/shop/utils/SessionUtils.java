package com.shop.utils;

import javax.servlet.http.HttpServletRequest;

public class SessionUtils {
    public static void put(String key, Object value, HttpServletRequest request){
        request.getSession().setAttribute(key,value);
    }

    public static Object get(String key, HttpServletRequest request){
        return request.getSession().getAttribute(key);
    }

    public static void remove(String key, HttpServletRequest request){
        request.getSession().removeAttribute(key);
    }
}
