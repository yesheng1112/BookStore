package com.ys.bookstore.bean;

import lombok.*;

import java.io.Serializable;

/*
* 用户类
* */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;//user的唯一标志
    private String username;//用户名
    private String password;//用户密码
    private String email;//用户邮箱

}
