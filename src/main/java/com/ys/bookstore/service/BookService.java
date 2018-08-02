package com.ys.bookstore.service;

import com.ys.bookstore.bean.Book;
import com.ys.bookstore.bean.Page;

import java.util.List;

/*
* 处理图书操作的业务逻辑
* */
public interface BookService {

    /*
    * 添加图书业务
    * */
    boolean addBook(Book book);

    /*
    * 删除图书业务
    * */
    boolean delBook(String bookId);

    /*
    * 修改图书业务
    * */
    boolean editBook(Book book);

    /*
    * 查询图书业务
    * */
    Book getBook(String bookId);

    /*
    * 查询所有图书业务
    * */
    List<Book> getBookList();

    /*
    * 查询分页的业务
    * */
    Page<Book> getPage(String pageNumber, int size);

    /*
    * 根据价格查询分页图书的业务
    * */
    Page<Book> getPageByPrice(String pageNumber, int size,String min,String max);
}
