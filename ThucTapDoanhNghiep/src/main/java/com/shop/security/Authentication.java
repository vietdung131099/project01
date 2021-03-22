package com.shop.security;

import com.shop.model.request.user.AuthRequest;

import javax.servlet.http.HttpServletRequest;

public interface Authentication {

    String authenticate(AuthRequest authRequest,HttpServletRequest request);
}
