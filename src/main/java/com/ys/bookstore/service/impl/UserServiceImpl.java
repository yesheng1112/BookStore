package com.ys.bookstore.service.impl;

import com.ys.bookstore.bean.User;
import com.ys.bookstore.dao.UserDao;
import com.ys.bookstore.dao.impl.UserDaoImpl;
import com.ys.bookstore.service.UserService;

public class UserServiceImpl implements UserService {

    //处理业务逻辑 登录和注册业务简单直接调用dao层就可以实现
    private UserDao dao = new UserDaoImpl();

    @Override
    public User login(User user) {
        return dao.getUserByUsernameAndPassword(user);
    }

    @Override
    public boolean regist(User user) {
        return dao.saveUser(user) >0;
    }

    @Override
    public boolean checkUsername(String username) {
        User user = dao.getUserByUsername(username);
        return user==null;
    }
}
