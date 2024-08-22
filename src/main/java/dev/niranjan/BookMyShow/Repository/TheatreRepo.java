package dev.niranjan.BookMyShow.Repository;

import dev.niranjan.BookMyShow.Model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepo extends JpaRepository<Theatre, Integer> {
    boolean existsByName(String theatreName);
    Theatre findByName(String theatreName);
}
