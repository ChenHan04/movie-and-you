package com.hanchen.controller;

import com.hanchen.entity.Movie;
import com.hanchen.service.MovieService;
import com.hanchen.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin
@RestController
@RequestMapping("/api/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/search")
    public Result searchMovies(@RequestParam String keyword) {
        List<Movie> movies = movieService.searchMovies(keyword);
        return Result.success(movies);
    }

    @GetMapping
    public Result searchMoviesByConditions(@RequestParam Map<String, String> params) {
        Map<String, Object> conditions = new HashMap<>(params);
        List<Movie> movies = movieService.searchMoviesByConditions(conditions);
        return Result.success(movies);
    }

    @GetMapping("/{id}")
    public Result getMovie(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);
        return Result.success(movie);
    }
}