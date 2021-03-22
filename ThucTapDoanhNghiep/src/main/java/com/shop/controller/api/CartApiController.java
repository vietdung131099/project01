package com.shop.controller.api;

import com.shop.bean.BeanFactory;
import com.shop.controller.BaseController;
import com.shop.entity.User;
import com.shop.model.request.cart.CartDisplayRequest;
import com.shop.model.request.cart.CartSaveRequest;
import com.shop.service.CartService;
import com.shop.utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cart/api/user")
public class CartApiController extends BaseController {
    private final CartService cartService;

    public CartApiController(){
        cartService = (CartService) BeanFactory.beans.get("cartService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartSaveRequest cartSaveRequest = toClass(req,CartSaveRequest.class);
        cartSaveRequest.setUserId(((User) SessionUtils.get("USER",req)).getId());
        cartService.save(cartSaveRequest);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action") == null ? null : "checkout";
        if(action==null){
            CartDisplayRequest cartDisplayRequest = toClass(req,CartDisplayRequest.class);
            cartService.delete(cartDisplayRequest);
        } else {
            int id = ((User) SessionUtils.get("USER",req)).getId();
            cartService.deleteAll(id);
        }

    }
}
