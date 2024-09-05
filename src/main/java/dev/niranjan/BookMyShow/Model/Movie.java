package dev.niranjan.BookMyShow.Model;

import dev.niranjan.BookMyShow.Model.Constant.MovieFeatures;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity

public class Movie extends BaseModel{
    private String title;
    private String description;
    private int year;
    private String director;
    private String genre;
    private double rating;
    @ManyToMany
    private List<Actor> actors;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<MovieFeatures> movieFeatures;

    public Movie(String name){
        this.title = name;
    }
    public Movie(){}


}
