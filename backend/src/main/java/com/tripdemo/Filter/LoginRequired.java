package com.tripdemo.Filter;

import com.tripdemo.entity.MyToken;
import com.tripdemo.mapper.UserMapper;
import com.tripdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(value = {"/indents/*",
        "/users/avatar/*",
        "/feedbacks/*",
        "/messages/*",
        "/comments/",
        "/favours/*"})
public class LoginRequired implements Filter {
//    @Autowired
    @Resource
    private UserService userService;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //根据请求头设置响应头
        String allowOrigin = request.getHeader("Origin");
        String allowHeader = request.getHeader("Access-Control-Request-Headers");
        response.setHeader("Access-Control-Allow-Origin", allowOrigin);
        //设置允许带cookie的请求
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "OPTIONS, POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", allowHeader);

        String tokenStr = request.getHeader("token");
        MyToken token = userService.getToken(tokenStr);

        if (token == null){
            resp.getWriter().append("request error");
        }
        else{
            if (userService.checkToken(token.getUserId(), tokenStr)){
                req.setAttribute("userId", token.getUserId());
                chain.doFilter(request, response);
            }
            else{
                resp.getWriter().append("request error");
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
