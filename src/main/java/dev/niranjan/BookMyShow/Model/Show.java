package dev.niranjan.BookMyShow.Model;

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
    @OneToMany(fetch = FetchType.LAZY)
    private List<ShowSeat> showSeats;
}