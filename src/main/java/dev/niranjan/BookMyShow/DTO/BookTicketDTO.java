package dev.niranjan.BookMyShow.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookTicketDTO {
    private List<Integer> showSeatIds;
    private int userId;
}
