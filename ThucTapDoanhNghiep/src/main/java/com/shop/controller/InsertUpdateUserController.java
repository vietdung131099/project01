package com.shop.controller;

import com.shop.bean.BeanFactory;
import com.shop.entity.Role;
import com.shop.entity.User;
import com.shop.model.request.user.UserSaveRequest;
import com.shop.service.RoleService;
import com.shop.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/user")
public class InsertUpdateUserController extends BaseController {

    private final UserService userService;
    private final RoleService roleService;

    public InsertUpdateUserController() {
        this.roleService = (RoleService) BeanFactory.beans.get("roleService");
        this.userService = (UserService) BeanFactory.beans.get("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rs = req.getRequestDispatcher("/views/user/insertUser.jsp");
        List<Role> roles = roleService.getAllRole();
        req.setAttribute("roles",roles);
        String action = req.getParameter("action");
        if (action.equals("update")){
            long id = Long.parseLong(req.getParameter("id"));
            User user = userService.findUserById(id);
            req.setAttribute("user",user);
            req.setAttribute("id",user.getId());
        }

        rs.forward(req,resp);
    }
}
