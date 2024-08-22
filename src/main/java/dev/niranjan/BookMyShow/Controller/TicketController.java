package dev.niranjan.BookMyShow.Controller;

import dev.niranjan.BookMyShow.DTO.BookTicketDTO;
import dev.niranjan.BookMyShow.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TicketController {
    @Autowired
    TicketService ticketService = new TicketService();

    @PostMapping("/bookTicket")
    public ResponseEntity bookTicket(@RequestBody BookTicketDTO bookTicketDTO) throws Exception {
        return ResponseEntity.ok(ticketService.bookTicket(bookTicketDTO.getShowSeatIds(), bookTicketDTO.getUserId()));
    }

}
