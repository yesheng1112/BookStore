package com.ys.bookstore.bean;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/*
* 分页类
* */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private List<T> data;//分页的图书集合

    @Setter
    private int pageNumber;//当前的页码，用户传入

    private int totalPage;//总页码可以计算得到

    private int index;//查询分页数据的起始索引，可以计算得到

    @Getter
    @Setter
    private int size;//每页显示的记录条数设置好的

    @Getter
    @Setter
    private int totalCount;//图书记录总条数，查询表得到的

    @Getter
    @Setter
    private String path;//访问当前分页的路径

    public int getPageNumber() {
        if (pageNumber<1){
            //如果当前页面小于1，返回1
            pageNumber = 1;
        }else if (pageNumber>getTotalPage()){
            pageNumber = getTotalPage();
        }
        return pageNumber;
    }

    /*
    * 计算得到，不需要设置方法
    *       根据totalCount和size计算得到
    * totalCount    size    totalPage
    * 10            2       5   totalCount/size
    *               3       4   totalCount/size+1
    *              11       1   totalCount/size+1
    * totalCount%size 如果能够被整除，总页数就是totalCount/size
    *   如果不能被整除totalCount/size+1
    *   page类中比较多的属性都是计算得到，我们在方法中使用属性时需要调用get方法来获取值
    * */
    public int getTotalPage() {
        if (getTotalCount()%getSize()==0)
            //整除
            return getTotalCount()/getSize();
        else
            return getTotalCount()/getSize()+1;
    }

    public int getIndex() {
        return getSize()*(getPageNumber()-1);
    }
}
