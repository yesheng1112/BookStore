package com.ys.bookstore.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* 解决乱码的Filter
* */
@WebFilter(filterName = "EncodeFilter",urlPatterns = "/*")
public class EncodeFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //解决乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //放行
        chain.doFilter(request,response);
    }

}
