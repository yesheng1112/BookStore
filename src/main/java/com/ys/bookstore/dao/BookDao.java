package com.ys.bookstore.dao;

import com.ys.bookstore.bean.Book;
import com.ys.bookstore.bean.Page;

import java.util.List;

/*
* 规范对bs_book表的操作
* */
public interface BookDao {

    /*
    * 根据id查询图书
    * */
    Book getBookById(String bookId);

    /*
    * 查询所有图书
    * */
    List<Book> getBookList();

    /*
    * 根据id删除图书
    * */
    int deleteBookById(String bookId);

    /*
    * 根据图书id修改图书
    * oldBook 已经存在数据库
    * newBook 替换老的图书，但是id不变
    * */
    int updateBookById(Book newBook);

    /*
    * 批量修改图书库存和销量的方法
    * */
    void batchUpdateSalesAndStock(Object[][] bookParams);

    /*
    * 保存图书
    * */
    int saveBook(Book book);

    /*
    * 查询分页图书
    * */
    Page<Book> getPageBook(Page<Book> page);

    /*
    * 根据价格查询分页数据
    * */
    Page<Book> getPageBookByPrice(Page<Book> page,double min,double max);
}
