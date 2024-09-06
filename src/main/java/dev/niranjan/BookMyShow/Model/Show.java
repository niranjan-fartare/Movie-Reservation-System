package dev.niranjan.BookMyShow.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.niranjan.BookMyShow.Model.Constant.ShowTiming;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "shows")
public class Show extends BaseModel{
    private String title;
    @Enumerated(EnumType.STRING)
    private ShowTiming showTiming;
    @ManyToOne
    private Movie movie;
    @OneToMany
    @JsonIgnore
    private List<ShowSeat> showSeats;
    @ManyToOne
    @JsonIgnore
    private Auditorium auditorium;
}