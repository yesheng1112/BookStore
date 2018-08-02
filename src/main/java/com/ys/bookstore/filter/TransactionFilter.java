package com.ys.bookstore.filter;

import com.ys.bookstore.utils.JDBCUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/*
* 统一处理事务的Filter
* */
@WebFilter(displayName = "TransactionFilter",urlPatterns = "/*")
public class TransactionFilter extends HttpFilter{
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        //获取连接
        Connection conn = JDBCUtils.getConnection();
        //开启事务
        try {
            conn.setAutoCommit(false);
            chain.doFilter(req,res);
            //如果没有发生异常，提交事务
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            //如果发生异常
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            //跳转到错误页面，给用户提示
            res.sendRedirect(req.getContextPath()+"/pages/error/error.jsp");
        } finally {
            //释放资源
            JDBCUtils.closeConnection();
        }
    }
}
