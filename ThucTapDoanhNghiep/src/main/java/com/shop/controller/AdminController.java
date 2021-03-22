package com.shop.controller;

import com.shop.bean.BeanFactory;
import com.shop.entity.User;
import com.shop.model.request.user.UserFilterRequest;
import com.shop.paging.PageResponse;
import com.shop.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.beancontext.BeanContext;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminController extends BaseController {

    private final UserService userService;

    public AdminController(){
        this.userService = (UserService) BeanFactory.beans.get("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/Admin.jsp");
//        UserFilterRequest userFilterRequest = mapToModel(UserFilterRequest.class, req);
//        PageResponse<User> users = null;
//        if(userFilterRequest != null){
//            users = userService.filterUser(userFilterRequest);
//        }
        List<User> users = userService.findAll();
        req.setAttribute("users",users);
        requestDispatcher.forward(req,resp);
    }

}
