package com.hanchen.mapper;

import com.hanchen.entity.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface MovieMapper {
    List<Movie> selectByKeyword(@Param("keyword") String keyword);
    Movie selectById(Long id);
    List<Movie> selectByConditions(@Param("conditions") Map<String, Object> conditions);
}