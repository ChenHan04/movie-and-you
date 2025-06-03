package com.hanchen.utils;

import lombok.Data;

@Data // 仅保留@Data注解，移除其他构造函数注解
public class Result {
    private int code;
    private String message;
    private Object data;

    // 显式定义无参构造函数
    public Result() {}

    // 显式定义全参构造函数
    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 成功静态方法
    public static Result success(Object data) {
        return new Result(200, "成功", data);
    }

    public static Result success(String message) {
        return new Result(200, message, null);
    }

    public static Result success(String message, Object data) {
        return new Result(200, message, data);
    }

    // 失败静态方法
    public static Result error(String message) {
        return new Result(500, message, null);
    }

    // 自定义错误码
    public static Result error(int code, String message) {
        return new Result(code, message, null);
    }
}