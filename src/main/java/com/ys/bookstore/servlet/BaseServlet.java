package com.ys.bookstore.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/*
* 提供给其他的Servlet继承BaseServlet：不需要在web.xml中注册，原理和HttpServlet一样
*
* 提取UserServlet和BaseServlet时的需要注意事项：
*   1.所有继承BaseServlet的子类都需要在web.xml中配置
*   2.在请求BaseServlet的子类时都需要在表单中添加一个参数，参数名一定是method，对应值一定要和子类的方法名一样
*   3.BaseServlet的子类继承BaseServlet，不能有doGet和doPost方法
*   4.子类方法的参数request和response
*
*   以后UserServlet处理新的用户请求时》要在表单中添加一个隐藏域name=method,值就是方法名
*   》在UserServlet中提供一个新的方法方法名就是新的需求
* */

public class BaseServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置request的编码格式，在过滤器中已经设置
        //this代表子类，request和子类中的一样
        String method = request.getParameter("method");
        //为了调用子类的方法名和method一样的方法
        //获取子类的类型
        Class<? extends BaseServlet> cla = this.getClass();
        //方法名和形参列表可以确定一个唯一的方法
        //根据子类的类型获取子类中一个确定方法的对象
        //参数1：要获取方法的方法名。
        //参数2：形参列表的类型
        try {
            Method m = cla.getDeclaredMethod(method,HttpServletRequest.class,HttpServletResponse.class);
            //调用方法 参数1：调用方法的对象 参数2：方法要的参数
            m.invoke(this,request,response);
        } catch (Exception e) {
           throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
