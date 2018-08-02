package com.ys.bookstore.servlet;

import com.ys.bookstore.bean.Order;
import com.ys.bookstore.service.OrderService;
import com.ys.bookstore.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderManagerServlet" , urlPatterns = "/OrderManagerServlet")
public class OrderManagerServlet extends BaseServlet{
    private static final long serialVersionUID = 1L;
    private OrderService service = new OrderServiceImpl();

    /*
    * 管理员查看所有的订单
    * */
    protected void getAllOrder(HttpServletRequest request , HttpServletResponse response)
                throws ServletException , IOException{
        List<Order> orderList = service.getOrderList();
        //存到域中
        request.setAttribute("orderList",orderList);
        //转发到/pages/manager/order_manager.jsp显示订单集合
        request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request,response);
    }

    protected void sendGoods(HttpServletRequest request , HttpServletResponse response)
                throws ServletException , IOException{
        //1.获取OrderId
        String orderId = request.getParameter("orderId");
        //2.设置订单要修改的状态[从未发货到已发货状态]
        int state = 1;
        //3.调用orderService处理业务
        service.updateState(orderId,state);
        //4.回到之前页面
        response.sendRedirect(request.getHeader("referer"));
    }
}
