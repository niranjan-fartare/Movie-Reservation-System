package dev.niranjan.BookMyShow.Model;

import dev.niranjan.BookMyShow.Model.Constant.ShowTiming;
import dev.niranjan.BookMyShow.Model.Constant.TicketStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Ticket extends BaseModel{
    @ManyToOne
    private User user;
    private ShowTiming showTime;
    private double amount;
    @OneToMany
    private List<ShowSeat> showSeats;
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;
}
