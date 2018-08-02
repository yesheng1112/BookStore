package com.ys.bookstore;

import com.ys.bookstore.bean.Book;
import com.ys.bookstore.bean.Page;
import com.ys.bookstore.dao.BookDao;
import com.ys.bookstore.dao.impl.BookDaoImpl;
import org.junit.Test;

import java.util.List;

public class TestBookDao {

    BookDao dao = new BookDaoImpl();

    @Test
    public void testSave(){
        //测试保存图书方法
        Book book = new Book(null,"ios从入门到转行","陈显东","/static/img/default.jpg",10,0,1000);
        int i = dao.saveBook(book);
        System.out.println(i);
    }

    @Test
    public void testSelect(){
        //测试查询图书方法
        List<Book> list = dao.getBookList();
        System.out.println(list);
    }

    @Test
    public void testPage(){
        //测试查询分页数据
        //模拟用户的访问过程
        //用户传入
        int pageNumber = 2;
        //servlet中设置
        int size = 4;
        //调用service的getPage方法处理业务逻辑
        Page<Book> page = new Page<>();
        page.setPageNumber(pageNumber);
        page.setSize(size);
        //调用BookDao的getPage方法查询相关数据
        Page<Book> pageBook = dao.getPageBook(page);
        System.out.println(pageBook);
    }

    @Test
    public void testPagePrice(){
        //测试查询分页数据
        //模拟用户的按照价格查询分页数据访问过程
        //用户传入
        int pageNumber = 3;
        //servlet中设置
        int size = 4;
        double min = 20;
        double max = 23;
        //调用service的getPage方法处理业务逻辑
        Page<Book> page = new Page<>();
        page.setPageNumber(pageNumber);
        page.setSize(size);
        //调用service的getPageByPrice方法查询相关数据
        Page<Book> pageBook = dao.getPageBookByPrice(page,min,max);
        System.out.println(pageBook);
    }
}
