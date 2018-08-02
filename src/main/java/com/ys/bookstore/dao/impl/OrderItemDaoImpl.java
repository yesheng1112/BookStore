package com.ys.bookstore.dao.impl;

import com.ys.bookstore.bean.OrderItem;
import com.ys.bookstore.dao.BaseDao;
import com.ys.bookstore.dao.OrderItemDao;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem item) {
        String sql = "insert into bs_orderitem(title, author, img_path, price, COUNT, amount, order_id) values (?,?,?,?,?,?,?)";
        return this.update(sql,item.getTitle(),item.getAuthor(),item.getImgPath(),item.getPrice(),item.getCount(),item.getAmount(),item.getOrderId());
    }

    @Override
    public List<OrderItem> getOrderItemListByOrderId(String orderId) {
        String sql = "select id,title,author,price,img_path imgPath,amount,count,order_id orderId from bs_orderitem where order_id = ?";
        return this.getBeanList(sql,orderId);
    }

    @Override
    public void batchSaveOrderItem(Object[][] params) {
        String sql = "insert into bs_orderitem(title,author,price,img_path,amount,count,order_id) values(?,?,?,?,?,?,?)";
        //this.update(sql,params);
        this.batchUpdate(sql,params);
    }
}
