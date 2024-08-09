package dev.niranjan.BookMyShow.Repository;

import dev.niranjan.BookMyShow.Model.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudiRepo extends JpaRepository<Auditorium, Integer> {
}
