package dev.niranjan.BookMyShow.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Show extends BaseModel{
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Movie movie;
    @ManyToOne
    private Auditorium auditorium;
    private List<ShowSeat> showSeats;
}
