package dev.niranjan.BookMyShow.Service;

import dev.niranjan.BookMyShow.DTO.ShowDTO;
import dev.niranjan.BookMyShow.Exception.AuditoriumNotFoundException;
import dev.niranjan.BookMyShow.Exception.ShowValidationException;
import dev.niranjan.BookMyShow.Model.*;
import dev.niranjan.BookMyShow.Model.Constant.ShowSeatStatus;
import dev.niranjan.BookMyShow.Repository.AudiRepo;
import dev.niranjan.BookMyShow.Repository.MovieRepo;
import dev.niranjan.BookMyShow.Repository.ShowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    private ShowRepo showRepo;
    @Autowired
    private MovieRepo movieRepo;
    @Autowired
    private ShowSeatService showSeatService;
    @Autowired
    private AuditoriumService auditoriumService;

    public boolean saveShow(ShowDTO showDTO) throws AuditoriumNotFoundException {
        if(showRepo.existsByShowTiming(showDTO.getShowTiming()) && showRepo.existsByTitle(showDTO.getTitle())) {
            throw new ShowValidationException("Show already exists");
        }
        Show show = new Show();
        show.setTitle(showDTO.getTitle());
        show.setShowTiming(showDTO.getShowTiming());
        Movie savedMovie = movieRepo.getReferenceById(showDTO.getMovieId());
        show.setMovie(savedMovie);

        Auditorium savedAuditorium = auditoriumService.getAuditoriumById(showDTO.getAuditoriumId());
        List<ShowSeat> showSeats = new ArrayList<>();
        Show savedShow = showRepo.save(show);
        for(int i=1;i<=savedAuditorium.getCapacity();i++){
            ShowSeat showSeat = new ShowSeat();
            showSeat.setPrice(1000);
            showSeat.setShow(show);
            showSeat.setSeat(savedAuditorium.getSeats().get(i-1));
            showSeat.setShowSeatStatus(ShowSeatStatus.AVAILABLE);
            showSeatService.saveShowSeat(showSeat);
            showSeats.add(showSeat);
        }
        savedShow.setShowSeats(showSeats);
        List<Show> shows;
        if(savedAuditorium.getShows() == null ){
            shows = new ArrayList<>();
            shows.add(savedShow);
            savedAuditorium.setShows(shows);
        } else savedAuditorium.getShows().add(savedShow);

        auditoriumService.saveAuditorium(savedAuditorium);

        showRepo.save(savedShow);

        return true;
    }

    public Show findById(int showId){
        return showRepo.getReferenceById(showId);
    }
}
