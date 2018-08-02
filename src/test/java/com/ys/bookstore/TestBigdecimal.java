package com.ys.bookstore;

import org.junit.Test;

import java.math.BigDecimal;

public class TestBigdecimal {

    //出现精度的误差
    @Test
    public void test(){
        double d1 = 0.1;
        double d2 = 0.1;
        double d3 = 0.1;
        System.out.println(d1+d2+d3);
    }

    //精确计算要用BigDecimal
    @Test
    public void testDouble(){
        double d1 = 0.1;
        double d2 = 0.1;
        double d3 = 0.1;
        //使用BigDecimal
        //使用double类型的数字转为BigDecimal对象
        BigDecimal bd1 = new BigDecimal(d1+"");
        BigDecimal bd2 = new BigDecimal(d2+"");
        BigDecimal bd3 = new BigDecimal(d3+"");
        //调用add方法相加
        BigDecimal sum = bd1.add(bd2);
        sum = sum.add(bd3);
        double d = sum.doubleValue();
        System.out.println(d);
    }

    //超过int的最大长度
    @Test
    public void testInt(){
        int k = 1;
        for (int i = 1; i <= 20; i++) {
            k*=i;
        }
        System.out.println(k);
    }
}
