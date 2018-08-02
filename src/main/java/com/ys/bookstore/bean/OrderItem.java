package com.ys.bookstore.bean;

import lombok.*;

/*
* 订单项类
* */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderItem {

    private Integer id;//订单项id
    private int count;//订单商品总数量
    private double amount;//订单项总金额
    private String title;//订单图书标题
    private String author;//订单图书作者
    private String imgPath;//订单图书封面
    private String orderId;//订单项所属的订单id，通过id将订单项和订单关联
    private double price;//订单图书单价
}
