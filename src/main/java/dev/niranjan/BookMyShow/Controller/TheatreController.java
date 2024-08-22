package dev.niranjan.BookMyShow.Controller;

import dev.niranjan.BookMyShow.DTO.TheatreDTO;
import dev.niranjan.BookMyShow.Exception.CityNotFoundException;
import dev.niranjan.BookMyShow.Exception.TheatreNotFoundException;
import dev.niranjan.BookMyShow.Exception.TheatreValidationException;
import dev.niranjan.BookMyShow.Model.Theatre;
import dev.niranjan.BookMyShow.Service.TheatreService;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TheatreController {
    @Autowired
    private TheatreService theatreService;

    @PostMapping("/theatre")
    public ResponseEntity<String> saveTheatre(@RequestBody TheatreDTO theatreDTO) {
        try{
            if(theatreService.saveTheatre(theatreDTO)){
                return ResponseEntity.status(HttpStatus.CREATED).body("Theatre added successfully");
            }
        } catch (TheatreValidationException e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (CityNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return null;
    }
    @GetMapping("/theatre/{theatreId}")
    public ResponseEntity<Object> getTheatre(@PathVariable int theatreId) {
        Theatre theatre;
        try{
            theatre = theatreService.getTheatreById(theatreId);
        } catch (TheatreNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(theatre);
    }

    @GetMapping("/theatres")
    public ResponseEntity<List<Theatre>> getAllTheatres() {
        return ResponseEntity.ok(theatreService.getAllTheaters());
    }

    @DeleteMapping("/theatre/{theatreId}")
    public ResponseEntity<String> deleteTheatre(@PathVariable Integer theatreId) {
        try{
            if(theatreService.deleteTheatreById(theatreId)){
                return ResponseEntity.status(HttpStatus.OK).body("Theatre deleted successfully");
            }
        } catch (TheatreValidationException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (TypeMismatchException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only numbers are allowed");
        }
        return null;
    }
}
