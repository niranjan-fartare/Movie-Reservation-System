package dev.niranjan.BookMyShow.Service;

import dev.niranjan.BookMyShow.DTO.AuditoriumDTO;
import dev.niranjan.BookMyShow.DTO.SeatDTO;
import dev.niranjan.BookMyShow.Exception.*;
import dev.niranjan.BookMyShow.Model.Auditorium;
import dev.niranjan.BookMyShow.Model.Constant.SeatStatus;
import dev.niranjan.BookMyShow.Model.Constant.SeatType;
import dev.niranjan.BookMyShow.Model.Seat;
import dev.niranjan.BookMyShow.Model.Theatre;
import dev.niranjan.BookMyShow.Repository.AudiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuditoriumService {
    @Autowired
    private AudiRepo audiRepo;
    @Autowired
    private TheatreService theatreService;
    @Autowired
    private SeatService seatService;

    public boolean saveAuditorium(AuditoriumDTO auditoriumDTO) throws AuditoriumValidationException, TheatreNotFoundException, SeatNotFoundException, SeatValidationException, AuditoriumNotFoundException {
        if(auditoriumDTO.getAudiNumber() == 0 || auditoriumDTO.getName().isEmpty()){
            throw new AuditoriumValidationException("Auditorium Name and Number cannot be empty");
        }

        if(audiRepo.existsByName(auditoriumDTO.getName()) && audiRepo.existsByAudiNumber(auditoriumDTO.getAudiNumber())){
            throw new AuditoriumValidationException("Auditorium already exists");
        }

        if(auditoriumDTO.getTheatreId() == 0){
            throw new AuditoriumValidationException("Theatre ID is required");
        }

        Auditorium auditorium = new Auditorium();
        auditorium.setAudiNumber(auditoriumDTO.getAudiNumber());
        auditorium.setName(auditoriumDTO.getName());
        auditorium.setAuditoriumFeatures(auditoriumDTO.getAuditoriumFeatures());
        auditorium.setCapacity(auditoriumDTO.getCapacity());
        auditorium.setTheatre(theatreService.getTheatreById(auditoriumDTO.getTheatreId()));
        Auditorium savedAuditorium = audiRepo.save(auditorium);

        List<Seat> seats = new ArrayList<>();
        for(int i=1;i<=auditoriumDTO.getCapacity();i++){
            SeatDTO seatDTO = new SeatDTO();
            seatDTO.setSeatNumber(i+""+i);
            seatDTO.setSeatType(SeatType.GOLD);
            seatDTO.setRow(i);
            seatDTO.setCol(i);
            seatDTO.setSeatStatus(SeatStatus.AVAILABLE);
            if(seatService.saveSeat(seatDTO)){
                seats.add(seatService.getSeatByRowAndCol(i,i));
            }
        }
        savedAuditorium.setSeats(seats);

        Theatre savedTheatre = theatreService.getTheatreById(auditoriumDTO.getTheatreId());
        List<Auditorium> auditoriums = savedTheatre.getAuditoriums();
        auditoriums.add(savedAuditorium);
        savedTheatre.setAuditoriums(auditoriums);

        theatreService.saveTheatre(savedTheatre);
        audiRepo.save(savedAuditorium);
        return true;
    }

    public Auditorium saveAuditorium(Auditorium auditorium){
        audiRepo.save(auditorium);
        return auditorium;
    }

    public Auditorium getAuditoriumById(int id) throws AuditoriumNotFoundException {
        if(audiRepo.existsById(id)){
            return audiRepo.findById(id).get();
        }
        throw new AuditoriumNotFoundException("Auditorium with id " + id + " does not exist");
    }

    public List<Auditorium> getAllAuditoriums() throws AuditoriumNotFoundException {
        if(!audiRepo.findAll().isEmpty()){
            return audiRepo.findAll();
        }
        throw new AuditoriumNotFoundException("No Auditoriums found");
    }

    public boolean deleteAuditoriumById(int id) throws AuditoriumNotFoundException {
        if(audiRepo.existsById(id)){
            audiRepo.deleteById(id);
            return true;
        }
        throw new AuditoriumNotFoundException("Auditorium with id " + id + " does not exist");
    }
}
