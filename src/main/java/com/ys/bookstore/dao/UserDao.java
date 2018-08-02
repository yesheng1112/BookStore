package com.ys.bookstore.dao;

import com.ys.bookstore.bean.User;

/*
* 约束bs_user表的操作
* */
public interface UserDao {

    /*
    * 根据账号密码查询user对象
    * @param user 封装了用户名和密码
    * @return 返回值封装了用户所有的信息
    * */
    User getUserByUsernameAndPassword(User user);

    /*
    * 检查用户名是否存在的方法
    * */
    User getUserByUsername(String username);

    /*
    * @param user 封装了账号密码email
    * @return 影响了几条数据，<=0代表插入失败
    * */
    int saveUser(User user);
}

