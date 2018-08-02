package com.ys.bookstore;

import com.ys.bookstore.bean.Book;
import com.ys.bookstore.bean.ShoppingCart;
import com.ys.bookstore.service.BookService;
import com.ys.bookstore.service.impl.BookServiceImpl;
import org.junit.Test;

public class TestShoppingCart {

    @Test
    public void test1(){
        //模拟用户添加图书的流程
        //1.提交bookId给CartServlet
        //2.servlet中使用bookService根据bookId查询对象
        //3.从session域中获取shoppingcart对象，如果有直接使用，调用添加图书到购物车的方法
        //如果没有，创建一个购物车对象存到session域中，调用添加图书到购物车的方法
        String bookId = "12";
        String bookId1 = "13";
        BookService bookService = new BookServiceImpl();
        Book book = bookService.getBook(bookId);
        Book book1 = bookService.getBook(bookId1);
        ShoppingCart shoppingCart = new ShoppingCart();
        //调用购物车的addBook2Cart(book)
        shoppingCart.addBook2Cart(book);
        shoppingCart.addBook2Cart(book);
        shoppingCart.addBook2Cart(book);
        shoppingCart.addBook2Cart(book1);
        shoppingCart.addBook2Cart(book1);
        System.out.println(shoppingCart);
        System.out.println(shoppingCart.getTotalAmount());
        System.out.println(shoppingCart.getTotalCount());
    }

    @Test
    public void test2(){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addBook2Cart(new Book(1, "heh", "chenxiandong", "hh", 0.1, 1, 1000));
        shoppingCart.addBook2Cart(new Book(1, "heh", "chenxiandong", "hh", 0.1, 1, 1000));
        shoppingCart.addBook2Cart(new Book(1, "heh", "chenxiandong", "hh", 0.1, 1, 1000));
        shoppingCart.addBook2Cart(new Book(2, "haha", "chenxiandong", "hh", 0.2, 1, 1000));
        shoppingCart.addBook2Cart(new Book(2, "haha", "chenxiandong", "hh", 0.2, 1, 1000));
        System.out.println(shoppingCart);
    }
}
