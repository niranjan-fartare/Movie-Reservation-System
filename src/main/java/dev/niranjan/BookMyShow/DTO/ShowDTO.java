package dev.niranjan.BookMyShow.DTO;

import dev.niranjan.BookMyShow.Model.Constant.ShowTiming;
import dev.niranjan.BookMyShow.Model.ShowSeat;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShowDTO {
    private String title;
    private ShowTiming showTiming;
    private int movieId;
    private int auditoriumId;
    private int numberOfShowSeats;
}