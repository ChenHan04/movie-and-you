package com.hanchen.service.impl;

import com.hanchen.mapper.FollowMapper;
import com.hanchen.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    private FollowMapper followMapper;

    @Override
    public boolean followUser(Long followerId, Long followedId) {
        return followMapper.insertFollow(followerId, followedId) > 0;
    }

    @Override
    public boolean unfollowUser(Long followerId, Long followedId) {
        return followMapper.deleteFollow(followerId, followedId) > 0;
    }
}
