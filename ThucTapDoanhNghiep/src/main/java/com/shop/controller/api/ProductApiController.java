package com.shop.controller.api;

import com.shop.bean.BeanFactory;
import com.shop.controller.BaseController;
import com.shop.model.request.product.ProductDeleteRequest;
import com.shop.model.request.product.ProductSaveRequest;
import com.shop.model.request.product.ProductUpdateRequest;
import com.shop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/product/api/user"})
public class ProductApiController extends BaseController {

    private final ProductService productService;

    public ProductApiController(){
        productService = (ProductService) BeanFactory.beans.get("productService");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDeleteRequest productDeleteRequest = toClass(req,ProductDeleteRequest.class);
        productService.delete(productDeleteRequest);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductSaveRequest productSaveRequest = toClass(req,ProductSaveRequest.class);
        productService.save(productSaveRequest);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductUpdateRequest productUpdateRequest = toClass(req,ProductUpdateRequest.class);
        productService.update(productUpdateRequest);
    }
}
