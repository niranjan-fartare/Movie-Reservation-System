package dev.niranjan.BookMyShow.Controller;

import dev.niranjan.BookMyShow.Exception.*;
import dev.niranjan.BookMyShow.Service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class InitController {
    @Autowired
    private InitService initService;

    @PostMapping("/init")
    public ResponseEntity<Object> init() throws TheatreNotFoundException, CityNotFoundException, CityValidationException, TheatreValidationException, AuditoriumValidationException {
        return ResponseEntity.status(HttpStatus.CREATED).body(initService.init());
    }
}
