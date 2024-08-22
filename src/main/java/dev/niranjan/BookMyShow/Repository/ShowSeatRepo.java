package dev.niranjan.BookMyShow.Repository;

import dev.niranjan.BookMyShow.Model.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatRepo extends JpaRepository<ShowSeat, Integer> {
}
