package dev.niranjan.BookMyShow.Service;

import dev.niranjan.BookMyShow.DTO.TicketResponseDTO;
import dev.niranjan.BookMyShow.Exception.TicketNotFoundException;
import dev.niranjan.BookMyShow.Model.Constant.ShowSeatStatus;
import dev.niranjan.BookMyShow.Model.Constant.TicketStatus;
import dev.niranjan.BookMyShow.Model.Seat;
import dev.niranjan.BookMyShow.Model.Show;
import dev.niranjan.BookMyShow.Model.ShowSeat;
import dev.niranjan.BookMyShow.Model.Ticket;
import dev.niranjan.BookMyShow.Repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    private ShowSeatService showSeatService;
    @Autowired
    private ShowService showService;
    @Autowired
    private TicketRepo ticketRepo;

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
        List<ShowSeat> showSeats = new ArrayList<>();
        for(Integer showSeatID : showSeatIDs){
            ShowSeat showSeat = showSeatService.getShowSeat(showSeatID);
            showSeats.add(showSeat);
        }
        Show show = showService.findById(showSeatIDs.get(0));
        ticket.setShowSeats(showSeats);
//        ticket.setShow(show);
        ticket.setShowTime(show.getShowTiming());
        ticket.setTicketStatus(TicketStatus.BOOKED);
        ticketRepo.save(ticket);
        return ticket;
    }

    public TicketResponseDTO findById(int ticketId){
        if(ticketRepo.findById(ticketId).isPresent()){
            Ticket ticket = ticketRepo.findById(ticketId).get();
            TicketResponseDTO ticketResponseDTO = new TicketResponseDTO();
            ticketResponseDTO.setAmount(ticket.getShowSeats().getFirst().getPrice());
            ticketResponseDTO.setStatus(ticket.getTicketStatus());
            ticketResponseDTO.setShowTiming(ticket.getShowTime());
            ticketResponseDTO.setMovieTitle(ticket.getShowSeats().getFirst().getShow().getMovie().getTitle());
            List<String> seats = new ArrayList<>();
            for(ShowSeat showSeat : ticket.getShowSeats()){
                seats.add(showSeat.getSeat().getSeatNumber());
            }
            ticketResponseDTO.setSeats(seats);
            return ticketResponseDTO;
        }
        throw new TicketNotFoundException("Ticket does not exist");
    }
}
