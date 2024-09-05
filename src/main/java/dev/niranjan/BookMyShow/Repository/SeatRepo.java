package dev.niranjan.BookMyShow.Repository;

import dev.niranjan.BookMyShow.Model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepo extends JpaRepository<Seat, Integer> {
    boolean existsByRowAndCol(int row, int column);
    Seat findByRowAndCol(int row, int column);
    Seat findBySeatNumber(String seatNumber);
    boolean existsBySeatNumber(String seatNumber);
    boolean deleteBySeatNumber(String seatNumber);
    boolean deleteByRowAndCol(int row, int column);
}
