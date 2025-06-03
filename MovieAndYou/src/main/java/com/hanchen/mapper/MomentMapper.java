package com.hanchen.mapper;

import com.hanchen.entity.Moment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MomentMapper {
    int insert(Moment moment);
    List<Moment> selectByUserId(@Param("userId") Long userId);
}