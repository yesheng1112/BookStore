package com.ys.bookstore.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/*
* 所有的web操作相关的工具类
* */
public class WebUtils {

    /*
    * 从request对象中获取参数自动封装给对应的javabean
    * 参数1：请求对象，为了获取请求参数集合
    * 参数2：集合要封装到那个对象中
    * */
    public static <T> T params2Bean(HttpServletRequest request,T t){
        //1.获取map 数据源
        Map<String ,String[]> map = request.getParameterMap();
        //2.调用BeanUtils方法将集合封装给t
        try {
            BeanUtils.populate(t,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public static String getPath(HttpServletRequest request){
        //a:manager/BookManagerServlet?method=findPage
        //uri:/BookStore/manager/BookManagerServlet
        String uri = request.getRequestURI();
        //queryString:method=findPage 参数字符串
        String queryString = request.getQueryString();
        //queryString中的pageNumber不需要使用，需要截取
        if (queryString != null && queryString.contains("&pageNumber")) {
            queryString = queryString.substring(0,queryString.indexOf("&pageNumber"));
        }
        //System.out.println(uri+"---"+queryString)
        String path = uri+"?"+queryString;
        return path;
    }
}
