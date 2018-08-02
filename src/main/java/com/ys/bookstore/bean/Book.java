package com.ys.bookstore.bean;

import lombok.*;

import java.io.Serializable;
//图书
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;//图书id
    private String title;//图书标题
    private String author;//图书作者
    private String imgPath;//图书封面地址
    private double price; //图书价格
    private int sales;//图书销量
    private int stock;//图书库存
}
