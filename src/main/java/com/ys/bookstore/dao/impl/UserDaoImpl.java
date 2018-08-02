package com.ys.bookstore.dao.impl;

import com.ys.bookstore.bean.User;
import com.ys.bookstore.dao.BaseDao;
import com.ys.bookstore.dao.UserDao;

/*
* 具体的对bs_user表的操作的实现
* 继承BaseDao：使用它的方法操作数据
* */
public class UserDaoImpl extends BaseDao<User> implements UserDao {
    /*
    * 如果返回值是null，代表查询不到用户，登录失败
    * 如果不为null，查询成功
    * */
    @Override
    public User getUserByUsernameAndPassword(User user) {
        String sql = "select id,username,password,email from bs_user where username = ? and password = ?";
        //用户名和密码起始在用户提交登录请求servlet获取之后传过来的
        return this.getBean(sql,user.getUsername(),user.getPassword());
    }

    @Override
    public User getUserByUsername(String username) {
        String sql = "select id,username,password,email from bs_user where username = ?";
        //用户名和密码起始在用户提交登录请求servlet获取之后传过来的
        return this.getBean(sql,username);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into bs_user(username,password,email) values(?,?,?)";
        //注册异常我们自己捕获
        try {
            return this.update(sql,user.getUsername(),user.getPassword(),user.getEmail());
        } catch (Exception e) {
            return 0;
        }
    }
}
