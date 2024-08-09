package dev.niranjan.BookMyShow.Repository;

import dev.niranjan.BookMyShow.Model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepo extends JpaRepository<Show, Integer> {
}
