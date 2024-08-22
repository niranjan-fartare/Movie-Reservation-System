package dev.niranjan.BookMyShow.Repository;

import dev.niranjan.BookMyShow.Model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepo extends JpaRepository<Seat, Integer> {
    boolean existsSeatByRowAndCol(int row, int column);
    Seat findSeatByRowAndCol(int row, int column);
    Seat findSeatBySeatNumber(int seatNumber);
    boolean existsSeatBySeatNumber(int seatNumber);
    boolean deleteSeatBySeatNumber(int seatNumber);
    boolean deleteSeatByRowAndCol(int row, int column);
}
