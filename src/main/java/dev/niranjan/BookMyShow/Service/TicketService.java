package dev.niranjan.BookMyShow.Service;

import dev.niranjan.BookMyShow.DTO.TicketResponseDTO;
import dev.niranjan.BookMyShow.Exception.SeatNotFoundException;
import dev.niranjan.BookMyShow.Exception.TheatreNotFoundException;
import dev.niranjan.BookMyShow.Exception.TicketNotFoundException;
import dev.niranjan.BookMyShow.Model.Constant.SeatStatus;
import dev.niranjan.BookMyShow.Model.Constant.ShowSeatStatus;
import dev.niranjan.BookMyShow.Model.Constant.TicketStatus;
import dev.niranjan.BookMyShow.Model.Seat;
import dev.niranjan.BookMyShow.Model.Show;
import dev.niranjan.BookMyShow.Model.ShowSeat;
import dev.niranjan.BookMyShow.Model.Ticket;
import dev.niranjan.BookMyShow.Repository.TicketRepo;
import dev.niranjan.BookMyShow.Repository.UserRepo;
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
    @Autowired
    private TheatreService theatreService;
    @Autowired
    private AuditoriumService auditoriumService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private SeatService seatService;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public boolean bookTicket(List<Integer> showSeatIDs, int userId) throws Exception {
        if(showSeatIDs.isEmpty() && userId == 0){
            throw new Exception("Show Seat IDs and User ID cannot be empty");
        }
        Ticket ticket = new Ticket();
        for(Integer showSeatID : showSeatIDs){
            ShowSeat showSeat = showSeatService.getShowSeat(showSeatID);
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                throw new Exception("Ticket is already Booked");
            }
        }
        for(Integer showSeatID : showSeatIDs){
            ShowSeat showSeat = showSeatService.getShowSeat(showSeatID);
            showSeat.setShowSeatStatus(ShowSeatStatus.BOOKED);
            showSeatService.saveShowSeat(showSeat);
        }
        List<ShowSeat> showSeats = new ArrayList<>();
        for(Integer showSeatID : showSeatIDs){
            ShowSeat showSeat = showSeatService.getShowSeat(showSeatID);
            showSeats.add(showSeat);
        }
        Show show = showService.findById(showSeatService.getShowSeat(showSeatIDs.getFirst()).getShowId());
        ticket.setShowSeats(showSeats);
        ticket.setUser(userRepo.getReferenceById(userId));
//        ticket.setShow(show);
        ticket.setShowTime(show.getShowTiming());
        ticket.setTicketStatus(TicketStatus.BOOKED);
        ticketRepo.save(ticket);
        return true;
    }

    public TicketResponseDTO findById(int ticketId) throws TheatreNotFoundException, SeatNotFoundException {
        if(ticketRepo.findById(ticketId).isPresent()){
            Ticket ticket = ticketRepo.findById(ticketId).get();
            TicketResponseDTO ticketResponseDTO = new TicketResponseDTO();
            ticketResponseDTO.setAmount(ticket.getShowSeats().getFirst().getPrice());
            ticketResponseDTO.setStatus(ticket.getTicketStatus());
            ticketResponseDTO.setShowTiming(ticket.getShowTime());
            ticketResponseDTO.setMovieTitle(showService.findById(ticket.getShowSeats().getFirst().getShowId()).getMovie().getTitle());
            List<String> seats = new ArrayList<>();
            for(ShowSeat showSeat : ticket.getShowSeats()){
                seats.add(seatService.getSeatById(showSeat.getSeatId()).getSeatNumber());
            }
            ticketResponseDTO.setAuditoriumName(showService.findById(ticket.getShowSeats().getFirst().getShowId()).getAuditorium().getName());
            ticketResponseDTO.setSeats(seats);
            ticketResponseDTO.setUserName(ticket.getUser().getName());
            ticketResponseDTO.setTheaterName(showService.findById(ticket.getShowSeats().getFirst().getShowId()).getAuditorium().getTheatre().getName());
            ticketResponseDTO.setTheaterAddress(showService.findById(ticket.getShowSeats().getFirst().getShowId()).getAuditorium().getTheatre().getAddress());
            return ticketResponseDTO;
        }
        throw new TicketNotFoundException("Ticket does not exist");
    }
}
