package com.ys.bookstore.bean;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/*
* 购物车类
* */
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
    * 购物项集合
    *   map的键就是CartItem中Book的id
    *   map的值就是对应的购物项
    * */
    @Getter
    @Setter
    private Map<String ,CartItem> map = new LinkedHashMap<>();

    /*
    * 购物项所有的商品总数量
    * 遍历map计算得到
    * */
    private int totalCount;

    /*
    * 所有商品的总金额
    * 遍历map计算得到
    * */
    private double totalAmount;

    public int getTotalCount() {
        //每次调用此方法初识化totalCount
        totalCount = 0;
        List<CartItem> itemList = getCartItemList();
        for (CartItem item : itemList) {
            totalCount+=item.getCount();
        }
        return totalCount;
    }

    public double getTotalAmount() {
        totalAmount = 0;
        BigDecimal bd01 = new BigDecimal(totalAmount+"");
        List<CartItem> itemList = getCartItemList();
        for (CartItem item : itemList) {
            BigDecimal bd02 = new BigDecimal(item.getAmount()+"");
            bd01 = bd01.add(bd02);
        }
        totalAmount = bd01.doubleValue();
        return totalAmount;
    }

    /*
    * 提供将map转为list的方法
    * */
    public List<CartItem> getCartItemList() {
        //将map所有的value封装到集合中
        Collection<CartItem> values = map.values();
        ArrayList<CartItem> list = new ArrayList<>(values);
        return list;
    }

    /*
    * 添加图书到购物车中
    * book对象是通过bookService.getBookById()方法查询到的
    * */
    public void addBook2Cart(Book book){
        //1.从map中查询购物项CartItem
        CartItem cartItem = map.get(book.getId()+"");
        //2.判断是否查询到购物项
        if (cartItem!=null){
            //2.1查到购物项 给购物项的数量+1
            cartItem.setCount(cartItem.getCount()+1);
        }else {
            //2.2没有对应的购物项 根据图书id查询图书创建为CartItem对象设置到map中
            //根据图书创建购物项
            //默认添加一本图书
            int count = 1;
            CartItem item = new CartItem(book,count);
            map.put(book.getId()+"",item);
        }
    }

    /*
    * 清空购物车的方法
    * */
    public void clearCart(){
        if (map!=null){
            map.clear();
        }
    }

    /*
    * 根据id删除指定购物项
    * */
    public void delCartItemByBookId(String bookId){
        map.remove(bookId);
    }

    /*
    * 更新购物项数量
    * 参数1：要修改的购物项
    * 参数2：修改的数量
    * */
    public void updateCount(String bookId,String count){
        CartItem cartItem = map.get(bookId);
        //i为默认值应该是购物项之前的数量
        int i = cartItem.getCount();
        try {
            i=Integer.parseInt(count);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        //修改cartItem的数量
        cartItem.setCount(i);
    }
}
