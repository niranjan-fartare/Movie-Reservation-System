package dev.niranjan.BookMyShow.Controller;

import dev.niranjan.BookMyShow.DTO.SeatDTO;
import dev.niranjan.BookMyShow.Exception.AuditoriumNotFoundException;
import dev.niranjan.BookMyShow.Exception.SeatNotFoundException;
import dev.niranjan.BookMyShow.Exception.SeatValidationException;
import dev.niranjan.BookMyShow.Model.Seat;
import dev.niranjan.BookMyShow.Service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SeatController {
    @Autowired
    private SeatService seatService;

    @PostMapping("/seat")
    public ResponseEntity<Object> saveSeat(@RequestBody SeatDTO seatDTO)  {
        try{
            seatService.saveSeat(seatDTO);
        } catch (SeatValidationException | AuditoriumNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Seat created successfully");
    }

    @GetMapping("/seat/{seatNumber}")
    public ResponseEntity<Object> getSeatBySeatNumber(@PathVariable String seatNumber) throws SeatNotFoundException {
        Seat seat;
        try{
            seat = seatService.getSeatBySeatNumber(seatNumber);
        } catch (SeatNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(seat);
    }

    @GetMapping("/seat/{row}/{col}")
    public ResponseEntity<Object> getSeatByRowAndCol(@PathVariable int row, @PathVariable int col) {
        Seat seat;
        try {
            seat = seatService.getSeatByRowAndCol(row, col);
        } catch (SeatNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(seat);
    }
    @GetMapping("/seats/{showId}")
    public ResponseEntity<Object> getSeatsByShowId(@PathVariable int showId) {
        List<Seat> seats;
        return null;
    }

}
