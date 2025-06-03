package com.hanchen.service.impl;

import com.hanchen.entity.Movie;
import com.hanchen.mapper.MovieMapper;
import com.hanchen.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieMapper movieMapper;

    @Override
    public List<Movie> searchMovies(String keyword) {
        return movieMapper.selectByKeyword("%" + keyword + "%");
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieMapper.selectById(id);
    }

    @Override
    public List<Movie> searchMoviesByConditions(Map<String, Object> conditions) {
        return movieMapper.selectByConditions(conditions);
    }
}