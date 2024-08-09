package dev.niranjan.BookMyShow.Repository;

import dev.niranjan.BookMyShow.Model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepo extends JpaRepository<Actor, Long> {
}
