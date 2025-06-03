package com.hanchen.entity;

import lombok.Data;

@Data
public class Movie {
    private Long id;
    private String titleCn;
    private String titleEn;
    private String director;
    private String actors;
    private Double doubanScore;
    private String posterUrl;
    private String genres;
    private String releaseDate;
}