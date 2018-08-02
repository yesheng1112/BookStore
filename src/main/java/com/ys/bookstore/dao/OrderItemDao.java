package com.ys.bookstore.dao;

import com.ys.bookstore.bean.OrderItem;

import java.util.List;

public interface OrderItemDao {

    /*
    * 保存订单项
    * */
    int saveOrderItem(OrderItem item);

    /*
    * 根据订单id查询所属订单项
    * */
    List<OrderItem> getOrderItemListByOrderId(String orderId);

    void batchSaveOrderItem(Object[][] params);
}
