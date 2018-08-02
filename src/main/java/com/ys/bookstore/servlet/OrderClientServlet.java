package com.ys.bookstore.servlet;

import com.ys.bookstore.bean.Order;
import com.ys.bookstore.bean.ShoppingCart;
import com.ys.bookstore.bean.User;
import com.ys.bookstore.service.OrderService;
import com.ys.bookstore.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderClientServlet" , urlPatterns = "/OrderClientServlet")
public class OrderClientServlet extends BaseServlet{

    private static final long serialVersionUID = 1L;
    private OrderService service = new OrderServiceImpl();

    /*
    * 处理用户的结账请求
    * 1.判读用户是否登录
    * */
    protected void checkOut(HttpServletRequest request , HttpServletResponse response)
                throws ServletException , IOException{
        //OrderService:处理业务时需要使用的数据user，cart都存在session域中
        HttpSession session = request.getSession();
        //用户在登录成功时将user对象存到session中
        User user = (User) session.getAttribute("user");
        //用户已登录，调用service完成结账
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        String orderId = service.createOrder(cart,user);
        request.setAttribute("orderId",orderId);
        //结账成功，清空购物车
        cart.clearCart();
        //跳转到结账成功页面显示订单编号
        request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(request,response);
    }

    protected void getOrderList(HttpServletRequest request , HttpServletResponse response)
                throws ServletException , IOException{
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //根据id查询订单
        List<Order> list = service.getOrderListByUserId(user.getId());
        request.setAttribute("orderList",list);
        request.getRequestDispatcher("/pages/order/order.jsp").forward(request,response);
    }

    /*
    * 确认收货
    * */
    protected void takeGoods(HttpServletRequest request , HttpServletResponse response)
                throws ServletException , IOException{
        String orderId = request.getParameter("orderId");
        int state = 2;
        boolean b = service.updateState(orderId,state);
        response.sendRedirect(request.getHeader("referer"));
    }
}
