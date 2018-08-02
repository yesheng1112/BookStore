package com.ys.bookstore.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
* 用户访问OrderClientServlet需要检查用户是否登录
* */
@WebFilter(displayName = "LoginFilter" ,urlPatterns = "/OrderClientServlet")
public class LoginFilter extends HttpFilter{
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");
        if (user==null){
            //用户未登录 跳转到登录页面
            //用户未登录，给出提示跳转到登录页面
            //转发之前设置错误消息
            String msg = "订单相关操作需要登录！！！！";
            //将字符串存到域中
            req.setAttribute("errorMsg",msg);
            //查询失败，用户不存在，转发到登录页面继续登录
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,res);
        }else {
            //用户已经登录放行
            chain.doFilter(req,res);
        }
    }
}
