package com.ys.bookstore.dao;

import com.ys.bookstore.bean.Order;

import java.util.List;

/*
* 约束bs_order表的操作
* */
public interface OrderDao {

    /*
    * 保存订单
    * */
    int saveOrder(Order order);

    /*
    * 根据用户id查询所属的订单集合
    * */
    List<Order> getOrderListByUserId(int userId);

    /*
    * 管理员查询所有的订单集合
    * */
    List<Order> getOrderList();

    /*
    * 用户和管理员修改订单状态使用
    * */
    int updateState(String orderId,int state);
}
