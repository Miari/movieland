package com.boroday.movieland.entity;

import lombok.Data;

@Data
public class Movie {
    private long id;
    private String nameRu;
    private String nameEn;
    private int yearOfProduction;
    private String description;
    private Double rating;
    private Double price;
}
