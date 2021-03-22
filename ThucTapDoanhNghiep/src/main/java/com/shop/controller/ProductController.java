package com.shop.controller;

import com.shop.bean.BeanFactory;
import com.shop.entity.Product;
import com.shop.repositories.ProductRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/product")
public class ProductController extends BaseController{

    private final ProductRepository productRepository;

    public ProductController(){
        productRepository = (ProductRepository) BeanFactory.beans.get("productRepository");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rs = req.getRequestDispatcher("/views/Product.jsp");
        List<Product> products = productRepository.findAll().collect(Collectors.toList());
        req.setAttribute("products",products);
        rs.forward(req,resp);
    }
}
