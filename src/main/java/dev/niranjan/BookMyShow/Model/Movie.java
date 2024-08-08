package dev.niranjan.BookMyShow.Model;

import dev.niranjan.BookMyShow.Model.Constant.MovieFeatures;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.List;

public class Movie extends BaseModel{
    private String title;
    private String description;
    private String year;
    private String director;
    private String genre;
    private String rating;
    @ManyToMany
    private List<Actor> actors;
    @Enumerated(EnumType.STRING)
    private List<MovieFeatures> movieFeatures;
}
