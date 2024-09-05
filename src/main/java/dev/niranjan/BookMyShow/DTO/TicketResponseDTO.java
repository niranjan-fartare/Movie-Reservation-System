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
    private ShowTiming showTiming;
    private Double amount;
    private List<String> seats;
    private TicketStatus status;
    private String movieTitle;
}
