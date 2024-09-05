package dev.niranjan.BookMyShow.Controller;

import dev.niranjan.BookMyShow.DTO.BookTicketDTO;
import dev.niranjan.BookMyShow.Model.Ticket;
import dev.niranjan.BookMyShow.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class TicketController {
    @Autowired
    TicketService ticketService = new TicketService();

    @PostMapping("/bookTicket")
    public ResponseEntity<Object> bookTicket(@RequestBody BookTicketDTO bookTicketDTO) throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.bookTicket(bookTicketDTO.getShowSeatIds(), bookTicketDTO.getUserId()))   ;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/ticket/{id}")
    public ResponseEntity<Object> getTicketById(@PathVariable int id) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(ticketService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
