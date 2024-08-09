package dev.niranjan.BookMyShow.Repository;

import dev.niranjan.BookMyShow.Model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepo extends JpaRepository<Seat, Integer> {
}
