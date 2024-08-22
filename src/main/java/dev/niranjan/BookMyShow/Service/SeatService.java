package dev.niranjan.BookMyShow.Service;

import dev.niranjan.BookMyShow.DTO.SeatDTO;
import dev.niranjan.BookMyShow.Exception.AuditoriumNotFoundException;
import dev.niranjan.BookMyShow.Exception.SeatNotFoundException;
import dev.niranjan.BookMyShow.Exception.SeatValidationException;
import dev.niranjan.BookMyShow.Model.Auditorium;
import dev.niranjan.BookMyShow.Model.Seat;
import dev.niranjan.BookMyShow.Repository.SeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatService {
    @Autowired
    private SeatRepo seatRepo;
    @Autowired
    private AuditoriumService auditoriumService;

    public boolean saveSeat(SeatDTO seatDTO) throws SeatValidationException, AuditoriumNotFoundException {
        if(seatRepo.existsSeatByRowAndCol(seatDTO.getRow(), seatDTO.getCol())){
            throw new SeatValidationException("Seat already exists");
        }
        Seat seat = new Seat();
        seat.setSeatNumber(seatDTO.getSeatNumber());
        seat.setRow(seatDTO.getRow());
        seat.setCol(seatDTO.getCol());
        seat.setSeatStatus(seatDTO.getSeatStatus());
        Seat savedSeat = seatRepo.save(seat);

        Auditorium savedAuditorium = auditoriumService.getAuditoriumById(seatDTO.getAuditoriumId());
        List<Seat> seats;
        if(savedAuditorium.getSeats().isEmpty()){
            seats = new ArrayList<>();
        } else seats = savedAuditorium.getSeats();

        seats.add(savedSeat);
        savedAuditorium.setSeats(seats);
        auditoriumService.saveAuditorium(savedAuditorium);

        return true;
    }
    public List<Seat> getAllSeats() {
        return seatRepo.findAll();
    }

    public Seat saveSeat(Seat seat){
        return seatRepo.save(seat);
    }

    public Seat getSeatByRowAndCol(int row, int col) throws SeatNotFoundException {
        if(seatRepo.existsSeatByRowAndCol(row, col)){
            return seatRepo.findSeatByRowAndCol(row, col);
        }
        throw new SeatNotFoundException("Seat does not exist");
    }

    public Seat getSeatBySeatNumber(int seatNumber) throws SeatNotFoundException {
        if(seatRepo.existsSeatBySeatNumber(seatNumber)){
            return seatRepo.findSeatBySeatNumber(seatNumber);
        }
        throw new SeatNotFoundException("Seat does not exist");
    }

    public boolean deleteSeatBySeatNumber(int seatNumber) throws SeatNotFoundException {
        if(seatRepo.existsSeatBySeatNumber(seatNumber)){
            seatRepo.deleteSeatBySeatNumber(seatNumber);
        }
        throw new SeatNotFoundException("Seat does not exists");
    }

    public boolean deleteSeatByRowAndCol(int row, int col) throws SeatNotFoundException {
        if(seatRepo.existsSeatByRowAndCol(row, col)){
            seatRepo.deleteSeatByRowAndCol(row, col);
        }
        throw new SeatNotFoundException("Seat does not exist");
    }

    public boolean deleteSeatById(int seatId) throws SeatNotFoundException {
        if (seatRepo.existsById(seatId)){
            seatRepo.deleteById(seatId);
        }
        throw new SeatNotFoundException("Seat does not exist");
    }

}
