package com.hanchen.controller;

import com.hanchen.service.FollowService;
import com.hanchen.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/api/follows")
public class FollowController {
    @Autowired
    private FollowService followService;

    @PostMapping("/{followedId}")
    public Result followUser(@RequestHeader("userId") Long followerId, @PathVariable Long followedId) {
        boolean success = followService.followUser(followerId, followedId);
        return success ? Result.success("关注成功") : Result.error("关注失败");
    }

    @DeleteMapping("/{followedId}")
    public Result unfollowUser(@RequestHeader("userId") Long followerId, @PathVariable Long followedId) {
        boolean success = followService.unfollowUser(followerId, followedId);
        return success ? Result.success("取消关注成功") : Result.error("取消关注失败");
    }
}