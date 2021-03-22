package com.shop.controller;

import com.shop.bean.BeanFactory;
import com.shop.entity.User;
import com.shop.model.request.user.AuthRequest;
import com.shop.security.Authentication;
import com.shop.service.UserService;
import com.shop.utils.ReflectionUtils;
import com.shop.utils.SessionUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = {"/login","/logout"})
public class LoginController extends BaseController {

    private final Authentication authentication;

    public LoginController(){
        this.authentication = (Authentication) BeanFactory.beans.get("authentication");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/Login.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action != null){
            if(action.equals("logout")){
                SessionUtils.remove("user",req);
                resp.sendRedirect("/login");
            }
        } else{
            AuthRequest authRequest = mapToModel(AuthRequest.class,req);
            String url = authentication.authenticate(authRequest,req);
            resp.sendRedirect(url);
        }

    }
}
