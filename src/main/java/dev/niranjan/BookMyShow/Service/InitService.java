package dev.niranjan.BookMyShow.Service;

import dev.niranjan.BookMyShow.DTO.AuditoriumDTO;
import dev.niranjan.BookMyShow.DTO.CityDTO;
import dev.niranjan.BookMyShow.DTO.ShowDTO;
import dev.niranjan.BookMyShow.DTO.TheatreDTO;
import dev.niranjan.BookMyShow.Exception.*;
import dev.niranjan.BookMyShow.Model.*;
import dev.niranjan.BookMyShow.Model.Constant.AuditoriumFeature;
import dev.niranjan.BookMyShow.Model.Constant.SeatStatus;
import dev.niranjan.BookMyShow.Model.Constant.SeatType;
import dev.niranjan.BookMyShow.Model.Constant.ShowTiming;
import dev.niranjan.BookMyShow.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class InitService {

    @Autowired
    private CityService cityService;

    @Autowired
    private TheatreService theatreService;

    @Autowired
    private AuditoriumService auditoriumService;

    @Autowired
    private SeatRepo seatRepo;

    @Autowired
    private TheatreRepo theatreRepo;
    @Autowired
    private AudiRepo audiRepo;
    @Autowired
    private MovieRepo movieRepo;
    @Autowired
    private ShowService showService;
    @Autowired
    private UserRepo userRepo;

    @Transactional
    public boolean init() throws CityValidationException, CityNotFoundException, TheatreValidationException, TheatreNotFoundException, AuditoriumValidationException, SeatValidationException, SeatNotFoundException, AuditoriumNotFoundException {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setName("Ahmednagar");
        cityDTO.setState("Maharashtra");
        cityService.saveCity(cityDTO);

        City savedCity = cityService.getCityByName("Ahmednagar");
        savedCity.setTheatreList(new ArrayList<>());

        TheatreDTO theatreDTO = new TheatreDTO();
        theatreDTO.setName("MyCinema");
        theatreDTO.setAddress("Savedi, Ahmednagar");
        theatreDTO.setCityId(cityService.getCityByName("Ahmednagar").getId());
        theatreService.saveTheatre(theatreDTO);

        Theatre savedTheatre = theatreService.getTheatreById(1);
        savedTheatre.setAuditoriums(new ArrayList<>());

        AuditoriumDTO auditoriumDTO = new AuditoriumDTO();
        auditoriumDTO.setAudiNumber(1);
        auditoriumDTO.setTheatreId(theatreRepo.getReferenceById(1).getId());
        auditoriumDTO.setCapacity(5);
        auditoriumDTO.setName("Audi01");
        ArrayList<AuditoriumFeature> auditoriumFeatures = new ArrayList<>();
        auditoriumFeatures.add(AuditoriumFeature.IMAX);
        auditoriumFeatures.add(AuditoriumFeature.THREED);
        auditoriumFeatures.add(AuditoriumFeature.TWOD);
        auditoriumDTO.setAuditoriumFeatures(auditoriumFeatures);

        auditoriumService.saveAuditorium(auditoriumDTO);

        Movie titanic = new Movie();
        titanic.setTitle("Titanic");
        titanic.setDescription("A good movie");
        titanic.setYear(1999);
        titanic.setDirector("John Rambo");
        titanic.setGenre("Drama");
        titanic.setRating(8.9);
        movieRepo.save(titanic);

        ShowDTO showDTO = new ShowDTO();
        showDTO.setTitle("Morning Show");
        showDTO.setShowTiming(ShowTiming.MORNING);
        showDTO.setMovieId(titanic.getId());
        showDTO.setAuditoriumId(auditoriumService.getAuditoriumById(1).getId());

        showService.saveShow(showDTO);
        User niranjan = new User();
        niranjan.setName("Niranjan");
        niranjan.setPassword("1234");
        niranjan.setEmail("me@niranjan.co");
        userRepo.save(niranjan);

        return true;
    }
}
