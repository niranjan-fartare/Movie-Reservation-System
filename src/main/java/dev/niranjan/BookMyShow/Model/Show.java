package dev.niranjan.BookMyShow.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "`show`")
public class Show extends BaseModel{
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private Auditorium auditorium;
    @OneToMany
    private List<ShowSeat> showSeats;
}