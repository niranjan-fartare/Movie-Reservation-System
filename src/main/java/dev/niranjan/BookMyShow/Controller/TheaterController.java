package dev.niranjan.BookMyShow.Controller;

import dev.niranjan.BookMyShow.Repository.TheaterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TheaterController {
    @Autowired
    TheaterRepo theaterRepo;
}
