package dev.niranjan.BookMyShow.Model;

import dev.niranjan.BookMyShow.Model.Constant.AuditoriumFeature;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
public class Auditorium extends BaseModel{
    private String name;
    private int audiNumber;
    private int capacity;
    @OneToMany
    private List<Seat> seats;
    @OneToMany
    private List<Show> shows;
    @ElementCollection // Used for Multiple Enums
    @Enumerated(EnumType.STRING) //Creates relation between Entity and Enums
    private List<AuditoriumFeature> auditoriumFeatures;
    @ManyToOne
    private Theatre theatre;
}