package dev.niranjan.BookMyShow.Controller;

import dev.niranjan.BookMyShow.DTO.AuditoriumDTO;
import dev.niranjan.BookMyShow.Exception.AuditoriumNotFoundException;
import dev.niranjan.BookMyShow.Exception.AuditoriumValidationException;
import dev.niranjan.BookMyShow.Exception.TheatreNotFoundException;
import dev.niranjan.BookMyShow.Model.Auditorium;
import dev.niranjan.BookMyShow.Service.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuditoriumController {
    @Autowired
    private AuditoriumService auditoriumService;
    @PostMapping("/auditorium")
    public ResponseEntity<Object> saveAuditorium(@RequestBody AuditoriumDTO auditoriumDTO){
        try{
            if(auditoriumService.saveAuditorium(auditoriumDTO)){
                return ResponseEntity.status(HttpStatus.CREATED).body("Auditorium added successfully");
            }
        } catch (AuditoriumValidationException | TheatreNotFoundException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
        }
        return null;
    }

    @GetMapping("/auditorium/{auditoriumId}")
    public ResponseEntity<Object> getAuditorium(@PathVariable int auditoriumId){
        Auditorium auditorium;
        try{
            auditorium = auditoriumService.getAuditoriumById(auditoriumId);
        } catch (AuditoriumNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(auditorium);
    }

    @GetMapping("/auditoriums")
    public ResponseEntity<Object> getAllAuditoriums() {
        List<Auditorium> auditoriums;
        try{
            auditoriums = auditoriumService.getAllAuditoriums();
        } catch (AuditoriumNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(auditoriums);
    }

    @DeleteMapping("/auditorium/{auditoriumId}")
    public ResponseEntity<String> deleteAuditorium(@PathVariable int auditoriumId) throws AuditoriumNotFoundException {
        if(auditoriumService.deleteAuditoriumById(auditoriumId)){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Auditorium deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Auditorium not found");
    }
}
