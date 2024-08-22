package dev.niranjan.BookMyShow.Service;

import dev.niranjan.BookMyShow.DTO.TheatreDTO;
import dev.niranjan.BookMyShow.Exception.CityNotFoundException;
import dev.niranjan.BookMyShow.Exception.TheatreNotFoundException;
import dev.niranjan.BookMyShow.Exception.TheatreValidationException;
import dev.niranjan.BookMyShow.Model.City;
import dev.niranjan.BookMyShow.Model.Theatre;
import dev.niranjan.BookMyShow.Repository.TheatreRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TheatreService {
    @Autowired
    private CityService cityService;
    @Autowired
    private TheatreRepo theatreRepo;

    @Transactional
    public boolean saveTheatre(TheatreDTO theatreDTO) throws TheatreValidationException, CityNotFoundException {
        if(theatreDTO.getName() == null) {
            throw new TheatreValidationException("Theatre name is required");
        }
        if(theatreRepo.existsByName(theatreDTO.getName()) &&
                theatreRepo.findByName(theatreDTO.getName()).getAddress().equals(theatreDTO.getAddress())) {
            throw new TheatreValidationException("There is already a theatre with that name and address");
        }
        Theatre theatre = new Theatre();
        theatre.setName(theatreDTO.getName());
        theatre.setAddress(theatreDTO.getAddress());

        Theatre savedTheatre = theatreRepo.save(theatre);

        City city = cityService.getCityById(theatreDTO.getCityId());
        List<Theatre> theaterList = city.getTheatreList();
        theaterList.add(savedTheatre);
        city.setTheatreList(theaterList);
        cityService.saveCity(city);
        return true;
    }
    public Theatre saveTheatre(Theatre theatre){
        return theatreRepo.save(theatre);
    }

    public List<Theatre> getAllTheaters(){
        return theatreRepo.findAll();
    }

    public Theatre getTheatreById(int theatreId) throws TheatreNotFoundException {
        return theatreRepo.findById(theatreId).orElseThrow(() ->
                new TheatreNotFoundException("Theatre with ID " + theatreId + " does not exist"));
    }

    @Transactional
    public boolean deleteTheatreById(int theatreId) throws TheatreValidationException {
        if(theatreRepo.existsById(theatreId)) {
            theatreRepo.deleteById(theatreId);
            return true;
        }
        throw new TheatreValidationException("Theatre with id " + theatreId + " does not exist");
    }
}
