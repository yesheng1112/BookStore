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

@WebServlet(name = "BookManagerServlet",urlPatterns = "/BookManagerServlet")
public class BookManagerServlet extends BaseServlet{

    private static final long serialVersionUID = 1L;
    private BookService service = new BookServiceImpl();

    protected void findPage(HttpServletRequest request, HttpServletResponse response)
                throws ServletException , IOException{
        String path = WebUtils.getPath(request);
        //path就是访问分页page对象的路径，可以给page类设置一个path属性用来绑定路径
        //1.获取用户要查询的页码
        String pageNumber = request.getParameter("pageNumber");
        //2.设置size 每页显示多少条数据
        int size = 4;
        //3.调用bookService处理业务
        Page<Book> page = service.getPage(pageNumber,size);
        //将路径设置给page对象
        page.setPath(path);
        //4.存入域中
        request.setAttribute("page",page);
        //5.转发到book_manager.jsp页面展示分页数据
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

    protected void deleteBook(HttpServletRequest request , HttpServletResponse response)
                throws ServletException , IOException{
        //referer:上一个页面的路径
        String referer = request.getHeader("referer");
        //1.获取参数 图书id
        String bookId = request.getParameter("bookId");
        //2.调用service处理删除
        boolean b = service.delBook(bookId);
        //3.删除后 回到图书显示页面
        //跳转到删除之前的页面
        response.sendRedirect(referer);
    }

    /*
    * 添加图书
    * */
    protected void addBook(HttpServletRequest request , HttpServletResponse response)
                throws ServletException,IOException{
        //将参数封装为book对象
        Book book = WebUtils.params2Bean(request,new Book());
        book.setImgPath("/static/img/default.jpg");
        //调用service将图书存到数据库中
        boolean b = service.addBook(book);
        //重定向到图书显示页面
        response.sendRedirect(request.getContextPath()+"/BookManagerServlet?method=findPage&pageNumber="+Integer.MAX_VALUE);
    }

    /*
    * 查询指定图书
    * */
    protected void getBook(HttpServletRequest request , HttpServletResponse response)
                throws ServletException , IOException{
        //获取修改之前的分页 路径
        String header = request.getHeader("referer");
        //存到request域中 共享
        request.setAttribute("referer",header);
        //1.获取图书id
        String bookId = request.getParameter("bookId");
        //2.查找图书
        Book book = service.getBook(bookId);
        //3.存入域中
        request.setAttribute("book",book);
        //4.转发到book_edit.jsp页面提供编辑
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);
    }

    /*
    * 修改图书信息
    * */
    protected void editBook(HttpServletRequest request ,HttpServletResponse response)
                throws ServletException , IOException{
        //1.将参数封装为图书对象
        Book book = WebUtils.params2Bean(request,new Book());
        //2.调用service完成修改
        boolean b = service.editBook(book);
        //3.跳转到book_manager.jsp页面显示所有图书信息
        //response.sendRedirect(request.getContextPath()+"/BookManagerServlet?method=findPage");
        //获取请求参数中的referer
        String referer = request.getParameter("referer");
        response.sendRedirect(referer);
    }
}
