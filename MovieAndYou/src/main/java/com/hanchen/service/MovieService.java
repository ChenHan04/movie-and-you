package com.hanchen.service;

import com.hanchen.entity.Movie;
import java.util.List;
import java.util.Map;

public interface MovieService {
    List<Movie> searchMovies(String keyword);
    Movie getMovieById(Long id);
    List<Movie> searchMoviesByConditions(Map<String, Object> conditions);
}