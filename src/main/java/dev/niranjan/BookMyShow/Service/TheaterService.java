package dev.niranjan.BookMyShow.Service;

import dev.niranjan.BookMyShow.Model.Theatre;
import dev.niranjan.BookMyShow.Repository.TheaterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheaterService {
    @Autowired
    private TheaterRepo theaterRepo;
    public Theatre saveTheater(String name){
        Theatre theatre = new Theatre();
        theatre.setName(name);
        return theaterRepo.save(theatre);
    }
}
