package com.ys.bookstore.dao.impl;

import com.ys.bookstore.bean.Book;
import com.ys.bookstore.bean.Page;
import com.ys.bookstore.dao.BaseDao;
import com.ys.bookstore.dao.BookDao;

import java.util.List;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {
    @Override
    public Book getBookById(String bookId) {
        String sql = "select id,title,author,price,sales,stock,img_path imgPath from bs_book where id =?";
        return this.getBean(sql,bookId);
    }

    @Override
    public List<Book> getBookList() {
        /*
        * 查询时，如果表的字段和javabean的字段不一致，一定要使用别名
        * beanUtils在封装对象时，会根据查找的表的字段去javabean中的找对应的set方法后面的属性名
        * */
        String sql = "select id,title,author,price,sales,stock,img_path imgPath from bs_book";
        return getBeanList(sql);
    }

    @Override
    public int deleteBookById(String bookId) {
        String sql = "delete from bs_book where id = ?";
        return this.update(sql,bookId);
    }

    @Override
    public int updateBookById(Book newBook) {
        String sql = "update bs_book set title =?,author=?,price=?,stock=?,img_path=? where id =?";
        return this.update(sql,
                newBook.getTitle(),newBook.getAuthor(),newBook.getPrice(),newBook.getStock(),newBook.getImgPath(),newBook.getId());
    }

    @Override
    public void batchUpdateSalesAndStock(Object[][] bookParams) {
        String sql = "update bs_book set sales=?,stock=? where id=?";
        this.batchUpdate(sql,bookParams);
    }

    @Override
    public int saveBook(Book book) {
        String sql = "insert into bs_book (title,author,price,sales,stock,img_path) values (?,?,?,?,?,?)";
        return this.update(sql,
                book.getTitle(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
    }

    @Override
    public Page<Book> getPageBook(Page<Book> page) {
        //BookService中调用此方法时：相当于有三个已知属性 pageNumber,size,index
        //1.查询图书记录总条数
        String sql = "select count(*) from bs_book";
        int totalCount = (int) this.getCount(sql);
        //将查询到的值设置给page对象 totalCount totalPage 都已知了
        page.setTotalCount(totalCount);
        //2.查询分页显示的图书集合
        String dataSql = "select id,title,author,price,sales,stock,img_path imgPath from bs_book limit ?,?";
        List<Book> list = this.getBeanList(dataSql,page.getIndex(),page.getSize());
        //将查询到的图书集合设置给page对象
        page.setData(list);
        return page;
    }

    @Override
    public Page<Book> getPageBookByPrice(Page<Book> page, double min, double max) {
        //page:pageNumber,size,index
        //1.查询记录的总条数 符合价格区间的记录
        String sql = "select count(*) from bs_book where price>=? and price<=?";
        int count = (int)this.getCount(sql,min,max);
        //2.将记录总数设置给page对象 totalPage可以计算得到
        page.setTotalCount(count);
        //3.根据index和size查询分页的图书集合
        sql = "select id,title,author,price,sales,stock,img_path imgPath from bs_book where price>=? and price<=? limit ?,?";
        List<Book> list = this.getBeanList(sql,min,max,page.getIndex(),page.getSize());
        //4.将图书集合设置给page对象
        page.setData(list);
        return page;
    }
}
