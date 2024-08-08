package dev.niranjan.BookMyShow.Model;

import dev.niranjan.BookMyShow.Model.Constant.SeatStatus;
import dev.niranjan.BookMyShow.Model.Constant.SeatType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{
    private String seatNumber;
    @Enumerated(EnumType.STRING)
    private SeatType seatType;
    private int row;
    private int column;
    @Enumerated(EnumType.STRING)
    private SeatStatus seatStatus;
}
