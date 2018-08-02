package com.ys.bookstore;

import com.ys.bookstore.bean.User;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class TestBeanUtils {

    /*
    * BeanUtils第三方jar包
    *   》可以将一个集合中的数据设置给一个对象[一个有数据的集合，一个空的集合，集合中存数据的key要和对象的属性名一样]
    *   》可以将一个对象转为一个集合
    *   1.导包
    * */

    @Test
    public void test(){
        //1.有数据的集合
        Map map = new HashMap();
        map.put("id",1234);
        map.put("username","wangyongchao1");
        map.put("password","123456");

        //2.空对象
        User user = new User();
        System.out.println(user);
        //3.调用BeanUtils的方法
        //参数1：空的对象，参数2：有数据的集合 数据源
        try {
            //LogFactory类找不到异常 BeanUtils有一个依赖包logging包
            //beanUtils根据反射找到getSet方法 设置属性值和读取属性值
            BeanUtils.populate(user,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(user);
    }

    @Test
    public void test1(){
        //将一个对象封装到集合中
        User user = new User(1,"laowang","yongchao","laowang@126.com");
        try {
            //bean的属性名作为key,属性值作为value存到map中,还有一个class。
            Map map = BeanUtils.describe(user);
            //{password=yongchao, id=1, class=class com.ys.bookstore.bean.User, email=laowang@126.com, username=laowang}
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
