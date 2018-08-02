package com.ys.bookstore.bean;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/*
* 购物项
* */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private Book book;//购物项对应的图书

    @Getter
    @Setter
    private int count;//当前图书的数量

    /*
    * 单品总金额
    * 计算得到【count*book.getPrice】
    * */
    @Setter
    private double amount;

    public CartItem(Book book, int count) {
        this.book = book;
        this.count = count;
    }

    public double getAmount() {
        BigDecimal bd01 = new BigDecimal(getBook().getPrice()+"");
        BigDecimal bd02 = new BigDecimal(getCount()+"");
        amount = bd01.multiply(bd02).doubleValue();
        return amount;
    }
}
