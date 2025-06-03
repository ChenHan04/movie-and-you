package com.hanchen.service;

import com.hanchen.entity.User;

public interface UserService {
    // 注册用户
    int register(User user);

    // 用户登录
    User login(String username, String password);
}