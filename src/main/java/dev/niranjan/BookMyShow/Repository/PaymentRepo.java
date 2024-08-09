package dev.niranjan.BookMyShow.Repository;

import dev.niranjan.BookMyShow.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer> {
}
