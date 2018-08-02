package com.ys.bookstore;

import com.ys.bookstore.bean.*;
import com.ys.bookstore.dao.OrderDao;
import com.ys.bookstore.dao.OrderItemDao;
import com.ys.bookstore.dao.impl.OrderDaoImpl;
import com.ys.bookstore.dao.impl.OrderItemDaoImpl;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class TestOrder {

    private OrderDao orderDao = new OrderDaoImpl();

    private OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Test
    public void testCreatOrder(){
        //1.判断用户是否登录
        //2.如果登录，在servlet中获取购物车对象，获取用户对象
        ShoppingCart cart = new ShoppingCart();
        Book b1 = new Book(1, "ios从入门到转行", "陈显东", "/static/img/default.jpg", 10, 0, 1000);
        Book b2 = new Book(2, "android从入门到转行", "陈筵席", "/static/img/default.jpg", 10, 0, 1000);
        Book b3 = new Book(3, "java从入门到转行", "阳光浴", "/static/img/default.jpg", 10, 0, 1000);
        cart.addBook2Cart(b1);
        cart.addBook2Cart(b1);
        cart.addBook2Cart(b1);
        cart.addBook2Cart(b2);
        cart.addBook2Cart(b2);
        cart.addBook2Cart(b3);
        User user = new User(2,"admin","123456","admin@126.com");
        //在Servlet中调用OrderService中createOrder方法处理业务
        //3.调用orderService：将购物车转为订单，购物项转为订单项
        //3.1将购物车转为订单
        //创建订单id 时间戳+userId
        String id = System.currentTimeMillis()+""+user.getId();
        //创建订单默认状态 默认为0 未发货
        int state = 0;
        //当前时间就是订单创建时间Date使用util包下的
        Date orderTime = new Date();
        Order order = new Order(id,cart.getTotalCount(),cart.getTotalAmount(),state,user.getId(),orderTime);
        //3.2将购物车的购物项转为订单项
        List<CartItem> list = cart.getCartItemList();
        //4.调用OrderDao和OrderItemDao将数据保存到数据库
        //调用OrderDao将订单保存到数据库
        System.out.println(orderDao.saveOrder(order));
        //每一个购物项对应一个订单对象
        for (CartItem cartItem : list) {
            Book book = cartItem.getBook();
            OrderItem orderItem = new OrderItem(null,cartItem.getCount(),cartItem.getAmount(),book.getTitle(),book.getAuthor(),book.getImgPath(),id,book.getPrice());
            System.out.println(orderItemDao.saveOrderItem(orderItem));
        }

        /*
        * 注意：
        *   1.插入订单项之前必须要先将对应的订单插入到数据库
        *   2.使用外键时，外键的值在关联的表中一定要存在
        * */
    }
}
