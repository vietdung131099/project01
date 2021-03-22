package com.shop.security;

import com.shop.entity.Role;
import com.shop.entity.User;
import com.shop.repositories.RoleRepository;
import com.shop.repositories.impl.RoleRepositoryImpl;
import com.shop.utils.SessionUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebFilter("/*")
public class Authorization implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String url = req.getRequestURI();
        if(url.startsWith("/login") || url.equals("/") || url.startsWith("/logout") || url.startsWith("/template") || url.startsWith("/home")){
            chain.doFilter(req,res);
            return;
        }
        User user = (User) SessionUtils.get("USER",req);
        if(user == null){
            RequestDispatcher ds = req.getRequestDispatcher("views/Login.jsp");
            ds.forward(req,res);
            return;
        }

        chain.doFilter(req,res);
    }

    @Override
    public void destroy() {

    }
}
