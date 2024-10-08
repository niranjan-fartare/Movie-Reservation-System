package dev.niranjan.BookMyShow.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
public class Actor extends BaseModel{
    private String name;
    private String address;
    private String email;
    private int phoneNumber;
    @ManyToMany
    private List<Movie> movies;

}