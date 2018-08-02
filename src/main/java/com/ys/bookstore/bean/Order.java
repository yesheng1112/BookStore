package com.ys.bookstore.bean;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/*
* 订单类
* */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;//订单编号
    private int totalCount;//订单总数量
    private double totalAmount;//订单总金额
    private int state;//订单状态
    private int userId;//订单所属用户id
    private Date orderTime;//下单时间
}
