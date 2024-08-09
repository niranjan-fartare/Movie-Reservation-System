package dev.niranjan.BookMyShow.Repository;

import dev.niranjan.BookMyShow.Model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepo extends JpaRepository<Theatre, Integer> {
}
