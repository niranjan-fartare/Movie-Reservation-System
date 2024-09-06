package dev.niranjan.BookMyShow.Service;

import dev.niranjan.BookMyShow.DTO.ShowSeatDTO;
import dev.niranjan.BookMyShow.Exception.SeatNotFoundException;
import dev.niranjan.BookMyShow.Exception.ShowSeatValidationException;
import dev.niranjan.BookMyShow.Model.Seat;
import dev.niranjan.BookMyShow.Model.ShowSeat;
import dev.niranjan.BookMyShow.Repository.ShowSeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowSeatService {
    @Autowired
    private ShowSeatRepo showSeatRepo;
    @Autowired
    private SeatService seatService;

    public boolean saveShowSeat(ShowSeatDTO showSeatDTO) throws SeatNotFoundException {
        if(showSeatRepo.existsBySeatIdAndShowId(showSeatDTO.getSeatId(), showSeatDTO.getShowId())){
            throw new ShowSeatValidationException("ShowSeat already exists");
        }
        ShowSeat showSeat = new ShowSeat();
        Seat savedSeat = seatService.getSeatById(showSeatDTO.getSeatId());
        showSeat.setSeatId(savedSeat.getId());
        return true;
    }

    public ShowSeat getShowSeat(int seatId) {
        return  showSeatRepo.findById(seatId).get();
    }

    public ShowSeat saveShowSeat(ShowSeat showSeat) {
        return showSeatRepo.save(showSeat);
    }
}
