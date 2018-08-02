package com.ys.bookstore.service;

import com.ys.bookstore.bean.User;

public interface UserService {
    User login(User user);
    boolean regist(User user);
    boolean checkUsername(String username);
}
