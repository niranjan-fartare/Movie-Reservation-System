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
    private String movieTitle;
    private ShowTiming showTiming;
    private String theaterName;
    private String theaterAddress;
    private String auditoriumName;
    private List<String> seats;
    private Double amount;
    private TicketStatus status;

}
