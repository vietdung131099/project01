package com.shop.controller.api;

import com.shop.bean.BeanFactory;
import com.shop.controller.BaseController;
import com.shop.entity.User;
import com.shop.model.request.user.UserDeleteRequest;
import com.shop.model.request.user.UserFilterRequest;
import com.shop.model.request.user.UserSaveRequest;
import com.shop.model.request.user.UserUpdateRequest;
import com.shop.paging.PageResponse;
import com.shop.service.PermissionService;
import com.shop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/admin/api/user"})
public class UserApiController extends BaseController {

    private final UserService userService;

    private final PermissionService permissionService;

    public UserApiController(){
        this.userService = (UserService) BeanFactory.beans.get("userService");
        this.permissionService = (PermissionService) BeanFactory.beans.get("permissionService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserFilterRequest userFilterRequest = mapToModel(UserFilterRequest.class,req);
        PageResponse<User> users = null;
        if (userFilterRequest != null){
            users = userService.filterUser(userFilterRequest);
        }
        // trả dữ liệu kiểu json từ servlet -> client
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(gson.toJson(users));
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDeleteRequest userDeleteRequest = toClass(req,UserDeleteRequest.class);
        permissionService.deleteRole(userDeleteRequest);
        userService.delete(userDeleteRequest);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserSaveRequest userSaveRequest = toClass(req,UserSaveRequest.class);
        userService.save(userSaveRequest);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserUpdateRequest userUpdateRequest = toClass(req,UserUpdateRequest.class);
        userService.update(userUpdateRequest);
    }
}
