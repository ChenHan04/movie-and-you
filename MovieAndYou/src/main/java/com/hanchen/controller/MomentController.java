package com.hanchen.controller;

import com.hanchen.entity.Moment;
import com.hanchen.service.MomentService;
import com.hanchen.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/moments")
public class MomentController {
    @Autowired
    private MomentService momentService;

    // 发布动态
    @PostMapping("/publish")
    public Result publishMoment(@RequestBody Moment moment) {
        int count = momentService.publishMoment(moment);
        return count > 0 ? Result.success("发布成功") : Result.error("发布失败");
    }

    // 获取用户动态
    @PostMapping("/user/{userId}")
    public Result getMomentsByUserId(@PathVariable Long userId) {
        List<Moment> moments = momentService.getMomentsByUserId(userId);
        return Result.success(moments);
    }
}