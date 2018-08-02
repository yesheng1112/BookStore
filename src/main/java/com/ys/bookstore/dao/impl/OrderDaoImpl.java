package com.ys.bookstore.dao.impl;

import com.ys.bookstore.bean.Order;
import com.ys.bookstore.dao.BaseDao;
import com.ys.bookstore.dao.OrderDao;

import java.util.List;

public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into bs_order(id,total_count,total_amount,order_time,state,user_id) values (?,?,?,?,?,?)";
        return this.update(sql,
                order.getId(),order.getTotalCount(),order.getTotalAmount(),order.getOrderTime(),
                order.getState(),order.getUserId());
    }

    @Override
    public List<Order> getOrderListByUserId(int userId) {
        String sql = "select id,total_count totalCount,total_amount totalAmount,order_time orderTime,state," +
                "user_id userId from bs_order where user_id = ? order by order_time desc";
        return this.getBeanList(sql,userId);
    }

    @Override
    public List<Order> getOrderList() {
        String sql = "select id,total_count totalCount,total_amount totalAmount,order_time orderTime," +
                "state,user_id userId from bs_order order by order_time desc";
        return this.getBeanList(sql);
    }

    @Override
    public int updateState(String orderId, int state) {
        String sql = "update bs_order set state = ? where id = ?";
        return this.update(sql,state,orderId);
    }
}
