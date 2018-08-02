package com.ys.bookstore;

import com.ys.bookstore.bean.User;
import org.junit.Test;

public class TestTreadLocal {

    @Test
    public void test(){
        //通过泛型执行local绑定数据的类型 相当于执行map的value的类型 Map<Thread,Object>
        ThreadLocal<User> local = new ThreadLocal<>();
        //给当前线程绑定一个对象 map.put(Tread.currentThread(),new User());
        local.set(new User());
        //获取当前线程绑定的对象 map.get(Tread.currentThread());
        User user = local.get();
        //移除当前线程绑定的对象 map.remove(Tread.currentThread());
        local.remove();
    }
}
