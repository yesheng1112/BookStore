package com.ys.bookstore;

import com.ys.bookstore.bean.User;
import com.ys.bookstore.dao.UserDao;
import com.ys.bookstore.dao.impl.UserDaoImpl;
import org.junit.Test;

public class TestUserDaoImpl {
    private UserDao dao = new UserDaoImpl();

    @Test
    public void testSave(){
        int i = dao.saveUser(new User(null,"zhangsan","123456","zhangsan@163.com"));
        System.out.println(i);
    }

    @Test
    public void testQuery(){
        User user = dao.getUserByUsernameAndPassword(new User(null,"adimin1","1234567",null));
        System.out.println(user);
    }
}
