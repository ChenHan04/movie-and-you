package com.hanchen.controller;

import com.hanchen.entity.User;
import com.hanchen.service.UserService;
import com.hanchen.utils.Result;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    // 注册接口
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        int count = userService.register(user);
        return count > 0 ? Result.success("注册成功") : Result.error("注册失败");
    }

    // 登录接口
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        User dbUser = userService.login(user.getUsername(), user.getPassword());
        if (dbUser != null) {
            // 这里可以生成JWT Token，暂时返回用户信息
            return Result.success(dbUser);
        }
        return Result.error("用户名或密码错误");
    }
}