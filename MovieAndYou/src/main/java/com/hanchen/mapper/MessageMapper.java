package com.hanchen.mapper;

import com.hanchen.entity.Message;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper {
    int insertMessage(Message message);
}