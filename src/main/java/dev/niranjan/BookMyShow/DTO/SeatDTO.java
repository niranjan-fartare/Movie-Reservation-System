package dev.niranjan.BookMyShow.DTO;

import dev.niranjan.BookMyShow.Model.Constant.SeatStatus;
import dev.niranjan.BookMyShow.Model.Constant.SeatType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatDTO {
    private String seatNumber;
    private SeatType seatType;
    private int row;
    private int col;
    private SeatStatus seatStatus;
    private SeatType type;
}
