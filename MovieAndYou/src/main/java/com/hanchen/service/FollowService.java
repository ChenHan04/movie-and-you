package com.hanchen.service;

public interface FollowService {
    boolean followUser(Long followerId, Long followedId);
    boolean unfollowUser(Long followerId, Long followedId);
}