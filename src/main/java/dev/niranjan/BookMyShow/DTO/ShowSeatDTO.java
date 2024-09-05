package dev.niranjan.BookMyShow.DTO;

import dev.niranjan.BookMyShow.Model.Constant.ShowSeatStatus;
import dev.niranjan.BookMyShow.Model.Seat;
import dev.niranjan.BookMyShow.Model.Show;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowSeatDTO {
    private int price;
    private int showId;
    private int seatId;
    private ShowSeatStatus showSeatStatus;
}
