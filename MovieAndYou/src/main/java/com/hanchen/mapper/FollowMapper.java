package com.hanchen.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FollowMapper {
    int insertFollow(@Param("followerId") Long followerId, @Param("followedId") Long followedId);
    int deleteFollow(@Param("followerId") Long followerId, @Param("followedId") Long followedId);
}