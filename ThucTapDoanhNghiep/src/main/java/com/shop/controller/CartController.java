package com.shop.controller;

import com.shop.bean.BeanFactory;
import com.shop.entity.User;
import com.shop.model.request.cart.CartDisplayRequest;
import com.shop.repositories.CartRepository;
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

@WebServlet("/cart")
public class CartController extends BaseController{
    private final CartRepository cartRepository;

    public CartController(){
        cartRepository = (CartRepository) BeanFactory.beans.get("cartRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(((User) SessionUtils.get("USER",req)) != null){
            String name = ((User) SessionUtils.get("USER",req)).getName();
            req.setAttribute("user",name);
        }

        RequestDispatcher rs = req.getRequestDispatcher("/views/Cart.jsp");
        int userId = ((User) SessionUtils.get("USER",req)).getId();
        List<CartDisplayRequest> products = cartRepository.showInCart(userId).collect(Collectors.toList());

        List<String> listStringPrice = products.stream().map(t->t.getPrice()).collect(Collectors.toList()); // vì trong database giá là để String
        List<Long> price = listStringPrice.stream().map(t->Long.parseLong(t)).collect(Collectors.toList());
        long total = 0;  // tổng tiền
        for(int i=0;i<price.size();i++){
            total += price.get(i);
        }

        req.setAttribute("tamtinh",total);
        req.setAttribute("tongtien",total+25000);
        req.setAttribute("products",products);
        rs.forward(req,resp);
    }
}
