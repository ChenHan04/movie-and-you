package com.hanchen.service.impl;

import com.hanchen.entity.User;
import com.hanchen.mapper.UserMapper;
import com.hanchen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    // 使用BCrypt加密密码
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public int register(User user) {
        // 加密密码
        user.setPassword(encoder.encode(user.getPassword()));
        return userMapper.insert(user);
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user != null && encoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }
}