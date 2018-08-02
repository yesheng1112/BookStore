package com.ys.bookstore.service;

import com.ys.bookstore.bean.Order;
import com.ys.bookstore.bean.ShoppingCart;
import com.ys.bookstore.bean.User;

import java.util.List;

public interface OrderService {
    /*
    * 处理了用户结账时创建订单的业务
    * */
    String createOrder(ShoppingCart cart, User user);

    List<Order> getOrderListByUserId(int userId);
    List<Order> getOrderList();

    /*
    * 修改订单状态的业务方法
    * */
    boolean updateState(String orderId,int state);
}
