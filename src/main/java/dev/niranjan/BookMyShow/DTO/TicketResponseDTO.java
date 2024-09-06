package dev.niranjan.BookMyShow.DTO;

import dev.niranjan.BookMyShow.Model.Constant.ShowTiming;
import dev.niranjan.BookMyShow.Model.Constant.TicketStatus;
import dev.niranjan.BookMyShow.Model.ShowSeat;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TicketResponseDTO {
    private String userName;
    private String theaterName;
    private String theaterAddress;
    private String auditoriumName;
    private ShowTiming showTiming;
    private List<String> seats;
    private Double amount;
    private TicketStatus status;
    private String movieTitle;
}
