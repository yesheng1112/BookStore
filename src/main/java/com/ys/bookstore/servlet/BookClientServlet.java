package com.ys.bookstore.servlet;


import com.ys.bookstore.bean.Book;
import com.ys.bookstore.bean.Page;
import com.ys.bookstore.service.BookService;
import com.ys.bookstore.service.impl.BookServiceImpl;
import com.ys.bookstore.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BookClientServlet",urlPatterns = "/BookClientServlet")
public class BookClientServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;
    private BookService service = new BookServiceImpl();

    protected void findPageByPrice(HttpServletRequest request, HttpServletResponse response)
                throws ServletException , IOException{
        //1.获取页码 最大价格 最小价格
        String min = request.getParameter("min");
        String max = request.getParameter("max");
        String pageNumber = request.getParameter("pageNumber");
        //2.设置每页显示条数
        int size = 4;
        //3.截取path路径
        String path = WebUtils.getPath(request);
        //4.调用service处理业务
        Page<Book> page = service.getPageByPrice(pageNumber,size,min,max);
        //5.给page绑定访问路径
        page.setPath(path);
        //6.page对象存到域中
        request.setAttribute("page",page);
        //7.转发到list.jsp页面展示分页信息
        request.getRequestDispatcher("/pages/list/list.jsp").forward(request,response);
    }

    protected void findPage(HttpServletRequest request , HttpServletResponse response)
                throws ServletException,IOException{
        //1.获取页码
        String pageNumber = request.getParameter("pageNumber");
        //2.设置每页显示的记录条数
        int size = 4;
        //3.截取路径
        String path = WebUtils.getPath(request);
        //4.调用service查询分页数据
        Page<Book> page = service.getPage(pageNumber,size);
        //设置路径
        page.setPath(path);
        //5.将page设置到request域中
        request.setAttribute("page",page);
        //6.转发到index.jsp页面显示图书集合
        request.getRequestDispatcher("/pages/list/list.jsp").forward(request,response);
    }
}
