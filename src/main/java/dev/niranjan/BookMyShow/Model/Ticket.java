package dev.niranjan.BookMyShow.Model;

import dev.niranjan.BookMyShow.Model.Constant.TicketStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public class Ticket extends BaseModel{
    private LocalDateTime showTime;
    private double amount;
    @OneToMany
    private List<ShowSeat> showSeats;
    @ManyToOne
    private Show show;
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;
}
