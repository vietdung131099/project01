package com.shop.controller;

import com.shop.bean.BeanFactory;
import com.shop.entity.Category;
import com.shop.entity.Product;
import com.shop.entity.Role;
import com.shop.entity.User;
import com.shop.service.CategoryService;
import com.shop.service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/product/user")
public class InsertUpdateProductController extends BaseController{

    private final CategoryService categoryService;
    private final ProductService productService;

    public InsertUpdateProductController(){
        categoryService = (CategoryService) BeanFactory.beans.get("categoryService");
        productService = (ProductService) BeanFactory.beans.get("productService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rs = req.getRequestDispatcher("/views/product/insertProduct.jsp");
        List<Category> categories = categoryService.getAllCategory().collect(Collectors.toList());
        req.setAttribute("categories",categories);

        String action = req.getParameter("action") == null ? "add" : "update";
        if (action.equals("update")){
            int id = Integer.parseInt(req.getParameter("id"));
            Product product = productService.findProductById(id);
            req.setAttribute("product",product);
        }
        rs.forward(req,resp);
    }
}
