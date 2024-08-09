package dev.niranjan.BookMyShow.Model;

import dev.niranjan.BookMyShow.Model.Constant.PaymentMode;
import dev.niranjan.BookMyShow.Model.Constant.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
@Entity
public class Payment extends BaseModel{
    private LocalDateTime paymentTime;
    private String referenceId;
    private double amount;
    @ManyToOne
    private Ticket ticket;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;
}
