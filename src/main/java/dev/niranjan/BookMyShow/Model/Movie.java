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
    private String year;
    private String director;
    private String genre;
    private String rating;
    @ManyToMany
    private List<Actor> actors;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<MovieFeatures> movieFeatures;
}
