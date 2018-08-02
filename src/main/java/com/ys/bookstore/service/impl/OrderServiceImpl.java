package com.ys.bookstore.service.impl;

import com.ys.bookstore.bean.*;
import com.ys.bookstore.dao.BookDao;
import com.ys.bookstore.dao.OrderDao;
import com.ys.bookstore.dao.OrderItemDao;
import com.ys.bookstore.dao.impl.BookDaoImpl;
import com.ys.bookstore.dao.impl.OrderDaoImpl;
import com.ys.bookstore.dao.impl.OrderItemDaoImpl;
import com.ys.bookstore.service.OrderService;

import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(ShoppingCart cart, User user) {
        //1.将购物车转为订单对象
        //创建订单id【唯一，便于售后查看 当前时间戳+用户id】
        String id = System.currentTimeMillis()+""+user.getId();
        //订单状态默认为0 未发货， 1已发货，2交易完成
        int state = 0;
        //当前时间就是订单创建时间
        Date orderTime = new Date();
        Order order = new Order(id,cart.getTotalCount(),cart.getTotalAmount(),state,user.getId(),orderTime);
        //3.将订单保存到数据库中
        orderDao.saveOrder(order);
        //获取购物车中的购物项集合
        List<CartItem> list = cart.getCartItemList();
        //创建两个二维数组
        //批量修改图书的库存销量的数组 第一维：sql语句执行次数，也就是集合的长度
        Object [][] bookParams = new Object[list.size()][];
        //批量插入订单项的数组
        Object [][] cartItemParams = new Object[list.size()][];
        //遍历将数据交给二维数组
        int i = 0;
        for (CartItem cartItem : list) {
            Book book = cartItem.getBook();
            int sales = book.getSales() + cartItem.getCount();//计算销售后的销量
            int stock = book.getStock() - cartItem.getCount();//计算销售后的库存
            if (stock<0){
                throw new RuntimeException("库存不能为负数");
            }
            //修改图书的销量和库存数据
            book.setSales(sales);
            book.setStock(stock);
            //sales=?,stock=? where id =?
            bookParams[i] = new Object[]{book.getSales(),book.getStock(),book.getId()};
            //title,author,price,img_path,amount,count,order_id
            cartItemParams[i] = new Object[]{book.getTitle(),book.getAuthor(),book.getPrice(),book.getImgPath(),cartItem.getAmount(),cartItem.getCount(),id};
            //调用bookDao将修改后的图书保存到数据库中bs_book
            //bookDao.updateBookById(book);
            //2.将购物项转为订单对象
            //OrderItem orderItem = new OrderItem(null,cartItem.getCount(),cartItem.getAmount(),book.getTitle(),book.getAuthor(),book.getImgPath(),id,book.getPrice());
            //3.将订单项保存到数据库中
            //orderItemDao.saveOrderItem(orderItem);
            i++;
        }
        //调用批处理方法
        bookDao.batchUpdateSalesAndStock(bookParams);
        orderItemDao.batchSaveOrderItem(cartItemParams);
        //返回订单编号
        return id;
    }

    @Override
    public List<Order> getOrderListByUserId(int userId) {
        return orderDao.getOrderListByUserId(userId);
    }

    @Override
    public List<Order> getOrderList() {
        return orderDao.getOrderList();
    }

    @Override
    public boolean updateState(String orderId, int state) {
        return orderDao.updateState(orderId,state) > 0;
    }
}
