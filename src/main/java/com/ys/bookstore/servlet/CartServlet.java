package com.ys.bookstore.servlet;

import com.google.gson.Gson;
import com.ys.bookstore.bean.Book;
import com.ys.bookstore.bean.CartItem;
import com.ys.bookstore.bean.ShoppingCart;
import com.ys.bookstore.service.BookService;
import com.ys.bookstore.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CartServlet" ,urlPatterns = "/CartServlet")
public class CartServlet extends BaseServlet{

    private static final long serialVersionUID = 1L;
    private BookService service = new BookServiceImpl();

    /*
    * 处理用户添加图书到购物车的请求
    * */
    protected void addBook2Cart(HttpServletRequest request , HttpServletResponse response)
                throws ServletException , IOException{
        //1.获取用户提交的图书id
        String bookId = request.getParameter("bookId");
        //2.调用BookService根据id查询图书对象
        Book book = service.getBook(bookId);

        //3.保存到购物车
        //3.1购物车保存在session中[一个会话代表一个用户，共享一个购物车]
        HttpSession session = request.getSession();
        //获取刚刚添加的图书标题 设置到session域中
        session.setAttribute("title",book.getTitle());
        //获取购物车并且判断是否存在
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        //3.2如果购物车对象 不存在 ，创建一个新的，存入到域中
        if (cart == null){
            cart = new ShoppingCart();
            session.setAttribute("cart",cart);
        }
        //3.3如果购物车对象存在可以直接使用
        cart.addBook2Cart(book);
        //处理之后跳转到修改之前页面
        //1.购物车图书的数量，2.刚刚添加的书名
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<>();
        //获取购物车商品总数量
        map.put("count",cart.getTotalCount());
        //获取修改的图书的书名
        map.put("title",book.getTitle());
        //通过Gson将map转为jsonStr
        String json = gson.toJson(map);
        response.getWriter().write(json);
    }

    /*
    * 处理清空购物车请求
    * */
    protected void clear(HttpServletRequest request , HttpServletResponse response)
                throws ServletException , IOException{
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart!=null){
            cart.clearCart();
        }
        response.sendRedirect(request.getHeader("referer"));
    }

    /*
    * 处理删除购物项的请求
    * 根据bookId从购物车map中删除购物项
    * */
    protected void delCartItem(HttpServletRequest request , HttpServletResponse response)
                throws ServletException , IOException{
        String booId = request.getParameter("bookId");
        HttpSession session = request.getSession();
        //获取购物车对象
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart != null){
            cart.delCartItemByBookId(booId);
        }
        //回到删除之前的页面
        response.sendRedirect(request.getHeader("referer"));
    }

    /*
    * 处理修改数量的请求
    * */
    protected void updateCount(HttpServletRequest request , HttpServletResponse response)
                throws ServletException , IOException{
        //获取图书id[根据id查找对应的购物项]，修改的数量
        String bookId = request.getParameter("bookId");
        String count = request.getParameter("count");
        //获取cart对象
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart != null){
            cart.updateCount(bookId,count);
        }
        CartItem item = cart.getMap().get(bookId);
        //响应数据给ajax[cart.totalCount,cart.totalAmount,cartItem.amount]
        Map<String , Object> map = new HashMap<>();
        map.put("totalCount",cart.getTotalCount());
        map.put("totalAmount",cart.getTotalAmount());
        map.put("amount",item.getAmount());
        Gson gson = new Gson();
        String json = gson.toJson(map);
        //写到响应体中
        response.getWriter().write(json);
    }
}
