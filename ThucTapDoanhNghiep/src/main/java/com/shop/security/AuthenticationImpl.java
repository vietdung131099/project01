package com.shop.security;

import com.shop.constant.annotation.Autowire;
import com.shop.constant.annotation.Component;
import com.shop.entity.Role;
import com.shop.entity.User;
import com.shop.model.request.user.AuthRequest;
import com.shop.service.RoleService;
import com.shop.service.UserService;
import com.shop.utils.SessionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AuthenticationImpl implements Authentication{

    @Autowire
    private final UserService userService;
    @Autowire
    private final RoleService roleService;

    public AuthenticationImpl(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @Override
    public String authenticate(AuthRequest authRequest, HttpServletRequest request) {
        Optional<User> user = userService.findByUserNameAndPassword(authRequest);
        String url = "";
        if(!user.isPresent()){
            return  "/login?message=Tai khoan hoac mat khau khong dung";
        }

        User currentUser = user.get();
        SessionUtils.put("USER",currentUser,request);

        List<Role> roles = roleService.getRoleByUserId(currentUser.getId());
        List<String> roleNames = roles
                .stream()
                .map(role -> role.getName())
                .collect(Collectors.toList());

        if(roleNames.contains("ADMIN")){
            return "/admin";
        }
        return "/home";
    }
}
