package dev.niranjan.BookMyShow.Service;

import dev.niranjan.BookMyShow.Model.ShowSeat;
import dev.niranjan.BookMyShow.Repository.ShowSeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowSeatService {
    @Autowired
    private ShowSeatRepo showSeatRepo;

    public ShowSeat getShowSeat(int seatId) {
        return  showSeatRepo.findById(seatId).get();
    }

    public ShowSeat saveShowSeat(ShowSeat showSeat) {
        return showSeatRepo.save(showSeat);
    }
}
