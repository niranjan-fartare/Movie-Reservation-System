package dev.niranjan.BookMyShow.Service;

import dev.niranjan.BookMyShow.DTO.AuditoriumDTO;
import dev.niranjan.BookMyShow.DTO.CityDTO;
import dev.niranjan.BookMyShow.DTO.TheatreDTO;
import dev.niranjan.BookMyShow.Exception.*;
import dev.niranjan.BookMyShow.Model.*;
import dev.niranjan.BookMyShow.Model.Constant.AuditoriumFeature;
import dev.niranjan.BookMyShow.Model.Constant.SeatStatus;
import dev.niranjan.BookMyShow.Model.Constant.SeatType;
import dev.niranjan.BookMyShow.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private ShowRepo showRepo;

    @Autowired
    private ShowSeatRepo showSeatRepo;

    @Autowired
    private MovieRepo movieRepo;
    @Autowired
    private TheatreRepo theatreRepo;
    @Autowired
    private AudiRepo audiRepo;

    public boolean init() throws CityValidationException, CityNotFoundException, TheatreValidationException, TheatreNotFoundException, AuditoriumValidationException {
        //Save City
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
        auditoriumDTO.setCapacity(10);
        auditoriumDTO.setName("Audi01");
        ArrayList<AuditoriumFeature> auditoriumFeatures = new ArrayList<>();
        auditoriumFeatures.add(AuditoriumFeature.IMAX);
        auditoriumFeatures.add(AuditoriumFeature.THREED);
        auditoriumFeatures.add(AuditoriumFeature.TWOD);
        auditoriumDTO.setAuditoriumFeatures(auditoriumFeatures);

        auditoriumService.saveAuditorium(auditoriumDTO);

        List<Seat> seats = new ArrayList<>();
        for(int i=1;i<=10;i++){
            Seat seat = new Seat();
            seat.setSeatNumber(i+""+i);
            seat.setSeatStatus(SeatStatus.AVAILABLE);
            seat.setSeatType(SeatType.GOLD);
            seat.setCol(i);
            seat.setRow(i);
            seatRepo.save(seat);
            seats.add(seat);
        }

        Auditorium savedAudi = audiRepo.findByName("Audi01");
        savedAudi.setSeats(seats);
        auditoriumService.saveAuditorium(savedAudi);




        return true;
    }
}
