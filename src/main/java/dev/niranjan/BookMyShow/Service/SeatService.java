package dev.niranjan.BookMyShow.Service;

import dev.niranjan.BookMyShow.DTO.SeatDTO;
import dev.niranjan.BookMyShow.Exception.AuditoriumNotFoundException;
import dev.niranjan.BookMyShow.Exception.SeatNotFoundException;
import dev.niranjan.BookMyShow.Exception.SeatValidationException;
import dev.niranjan.BookMyShow.Model.Seat;
import dev.niranjan.BookMyShow.Repository.SeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    @Autowired
    private SeatRepo seatRepo;

    public boolean saveSeat(SeatDTO seatDTO) throws SeatValidationException, AuditoriumNotFoundException {
        if(seatRepo.existsByRowAndCol(seatDTO.getRow(), seatDTO.getCol())){
            throw new SeatValidationException("Seat already exists");
        }
        Seat seat = new Seat();
        seat.setSeatNumber(seatDTO.getSeatNumber());
        seat.setRow(seatDTO.getRow());
        seat.setCol(seatDTO.getCol());
        seat.setSeatType(seatDTO.getSeatType());
        seat.setSeatStatus(seatDTO.getSeatStatus());
        seatRepo.save(seat);

        return true;
    }
    public List<Seat> getAllSeats() {
        return seatRepo.findAll();
    }

    public Seat saveSeat(Seat seat){
        return seatRepo.save(seat);
    }

    public Seat getSeatByRowAndCol(int row, int col) throws SeatNotFoundException {
        if(seatRepo.existsByRowAndCol(row, col)){
            return seatRepo.findByRowAndCol(row, col);
        }
        throw new SeatNotFoundException("Seat does not exist");
    }
    public Seat getSeatById(int id) throws SeatNotFoundException {
        if(seatRepo.existsById(id)){
            return seatRepo.findById(id).get();
        }
        throw new SeatNotFoundException("Seat does not exist");
    }

    public Seat getSeatBySeatNumber(String seatNumber) throws SeatNotFoundException {
        if(seatRepo.existsBySeatNumber(seatNumber)){
            return seatRepo.findBySeatNumber(seatNumber);
        }
        throw new SeatNotFoundException("Seat does not exist");
    }

    public boolean deleteSeatBySeatNumber(String seatNumber) throws SeatNotFoundException {
        if(seatRepo.existsBySeatNumber(seatNumber)){
            seatRepo.deleteBySeatNumber(seatNumber);
        }
        throw new SeatNotFoundException("Seat does not exists");
    }

    public boolean deleteSeatByRowAndCol(int row, int col) throws SeatNotFoundException {
        if(seatRepo.existsByRowAndCol(row, col)){
            seatRepo.deleteByRowAndCol(row, col);
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
