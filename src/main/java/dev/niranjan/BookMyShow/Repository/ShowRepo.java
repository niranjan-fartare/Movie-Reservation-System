package dev.niranjan.BookMyShow.Repository;

import dev.niranjan.BookMyShow.Model.Constant.ShowTiming;
import dev.niranjan.BookMyShow.Model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ShowRepo extends JpaRepository<Show, Integer> {
    boolean existsByShowTiming(ShowTiming startTime);
    boolean existsByTitle(String title);
}
