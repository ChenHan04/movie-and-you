package com.hanchen.controller;

import com.hanchen.entity.Movie;
import com.hanchen.service.MovieService;
import com.hanchen.utils.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MovieControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
    }

    @Test
    public void testSearchMovies() throws Exception {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitleCn("Test Movie");
        List<Movie> movies = Arrays.asList(movie);

        when(movieService.searchMovies("Test")).thenReturn(movies);

        mockMvc.perform(get("/api/movies/search")
                        .param("keyword", "Test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\":200,\"message\":\"成功\",\"data\":[{\"id\":1,\"titleCn\":\"Test Movie\",\"titleEn\":null,\"director\":null,\"actors\":null,\"doubanScore\":null,\"posterUrl\":null,\"genres\":null,\"releaseDate\":null}]}"));
    }

    @Test
    public void testSearchMoviesByConditions() throws Exception {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitleCn("Test Movie");
        List<Movie> movies = Arrays.asList(movie);

        Map<String, Object> conditions = new HashMap<>();
        conditions.put("type", "科幻");
        conditions.put("year", "2020-2025");
        conditions.put("score", "8+");

        when(movieService.searchMoviesByConditions(conditions)).thenReturn(movies);

        mockMvc.perform(get("/api/movies")
                        .param("type", "科幻")
                        .param("year", "2020-2025")
                        .param("score", "8+")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\":200,\"message\":\"成功\",\"data\":[{\"id\":1,\"titleCn\":\"Test Movie\",\"titleEn\":null,\"director\":null,\"actors\":null,\"doubanScore\":null,\"posterUrl\":null,\"genres\":null,\"releaseDate\":null}]}"));
    }

    @Test
    public void testGetMovie() throws Exception {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitleCn("Test Movie");

        when(movieService.getMovieById(1L)).thenReturn(movie);

        mockMvc.perform(get("/api/movies/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\":200,\"message\":\"成功\",\"data\":{\"id\":1,\"titleCn\":\"Test Movie\",\"titleEn\":null,\"director\":null,\"actors\":null,\"doubanScore\":null,\"posterUrl\":null,\"genres\":null,\"releaseDate\":null}}"));
    }
}