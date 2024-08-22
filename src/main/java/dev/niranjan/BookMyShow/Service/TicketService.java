package dev.niranjan.BookMyShow.Service;

import dev.niranjan.BookMyShow.Model.Constant.ShowSeatStatus;
import dev.niranjan.BookMyShow.Model.ShowSeat;
import dev.niranjan.BookMyShow.Model.Ticket;
import dev.niranjan.BookMyShow.Repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private ShowSeatService showSeatService;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Ticket bookTicket(List<Integer> showSeatIDs, int userId) throws Exception {
        Ticket ticket = new Ticket();
        for(Integer showSeatID : showSeatIDs){
            ShowSeat showSeat = showSeatService.getShowSeat(showSeatID);
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                throw new Exception("Ticket is already Booked");
            }
        }
        for(Integer showSeatID : showSeatIDs){
            ShowSeat showSeat = showSeatService.getShowSeat(showSeatID);
            showSeat.setShowSeatStatus(ShowSeatStatus.LOCKED);
            showSeatService.saveShowSeat(showSeat);
        }
        return ticket;
    }
}
