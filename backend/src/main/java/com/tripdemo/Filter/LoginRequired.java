package com.tripdemo.Filter;

import com.tripdemo.entity.MyToken;
import com.tripdemo.mapper.UserMapper;
import com.tripdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(value = {"/indents/*",
        "/users/avatar/*",
        "/feedbacks/*"})
public class LoginRequired implements Filter {
//    @Autowired
    @Resource
    private UserService userService;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        String tokenStr = request.getHeader("token");
        MyToken token = userService.getToken(tokenStr);

        if (token == null){
            resp.getWriter().append("request error");
        }
        else{
            req.setAttribute("userId", token.getUserId());
            chain.doFilter(request, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
