package com.hanchen.mapper;

import com.hanchen.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int insert(User user);
    User selectByUsername(String username);
    int deleteByUsername(@Param("username") String username);
}