package com.ys.bookstore.service.impl;

import com.ys.bookstore.bean.Book;
import com.ys.bookstore.bean.Page;
import com.ys.bookstore.dao.BookDao;
import com.ys.bookstore.dao.impl.BookDaoImpl;
import com.ys.bookstore.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    //所有的数据库操作都是通过dao完成
    private BookDao dao = new BookDaoImpl();
    @Override
    public boolean addBook(Book book) {
        return dao.saveBook(book)>0;
    }

    @Override
    public boolean delBook(String bookId) {
        return dao.deleteBookById(bookId)>0;
    }

    @Override
    public boolean editBook(Book book) {
        return dao.updateBookById(book)>0;
    }

    @Override
    public Book getBook(String bookId) {
        return dao.getBookById(bookId);
    }

    @Override
    public List<Book> getBookList() {
        return dao.getBookList();
    }

    @Override
    public Page<Book> getPage(String pageNumber, int size) {
        //创建page对象
        Page<Book> page = new Page<>();
        //默认查询第一页
        int no = 1;
        if (pageNumber!=null) {
            try {
                //捕获数字转化异常
                no = Integer.parseInt(pageNumber);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        page.setPageNumber(no);
        page.setSize(size);
        return dao.getPageBook(page);
    }

    @Override
    public Page<Book> getPageByPrice(String pageNumber, int size, String min, String max) {
        Page<Book> page = new Page<>();
        int no = 1;
        if (pageNumber!=null) {
            try {
                no = Integer.parseInt(pageNumber);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        double minVal = Double.MIN_VALUE;
        double maxVal = Double.MAX_VALUE;
        if (min!=null) {
            try {
                minVal = Double.parseDouble(min);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        if (max!=null) {
            try {
                maxVal = Double.parseDouble(max);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        //设置给page对象
        page.setPageNumber(no);
        page.setSize(size);
        //调用dao完成数据查询
        Page<Book> pageBookByPrice = dao.getPageBookByPrice(page,minVal,maxVal);
        return pageBookByPrice;
    }
}
