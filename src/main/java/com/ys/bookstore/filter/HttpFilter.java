package com.ys.bookstore.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class HttpFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        doFilter(request, response,chain);
    }
    public abstract void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)throws IOException, ServletException;

    public void init(FilterConfig config) throws ServletException {

    }

}
