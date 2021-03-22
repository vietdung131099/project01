package com.shop.controller;

import com.shop.bean.BeanFactory;
import com.shop.entity.Product;
import com.shop.entity.User;
import com.shop.paging.PageRequest;
import com.shop.paging.PageResponse;
import com.shop.repositories.ProductRepository;
import com.shop.utils.SessionUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/home")
public class HomeController extends BaseController {

    private final ProductRepository productRepository;
    private String product;

    public HomeController(){
        this.productRepository = (ProductRepository) BeanFactory.beans.get("productRepository");

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(((User) SessionUtils.get("USER",req)) != null){
            String name = ((User) SessionUtils.get("USER",req)).getName();
            req.setAttribute("user",name);
        }


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/Home.jsp");
        product = req.getParameter("product") == null ? "all" : req.getParameter("product");
        List<Product> products = productRepository.findAll().collect(Collectors.toList());
        if(product.equals("quan")){
            products = productRepository.findByCategoryId(1).collect(Collectors.toList());
        } else if(product.equals("ao")){
            products = productRepository.findByCategoryId(2).collect(Collectors.toList());
        } else if(product.equals("giay")){
            products = productRepository.findByCategoryId(3).collect(Collectors.toList());
        }

        req.setAttribute("products",products);
        requestDispatcher.forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


}
