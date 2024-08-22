package dev.niranjan.BookMyShow.Controller;

import dev.niranjan.BookMyShow.DTO.CityDTO;
import dev.niranjan.BookMyShow.Exception.CityNotFoundException;
import dev.niranjan.BookMyShow.Exception.CityValidationException;
import dev.niranjan.BookMyShow.Model.City;
import dev.niranjan.BookMyShow.Service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityController {
    @Autowired
    private CityService cityService;

    @PostMapping("/city")
    public ResponseEntity<String> saveCity(@RequestBody CityDTO cityDTO) {
        try {
            if (cityService.saveCity(cityDTO)) {
                return ResponseEntity.status(HttpStatus.CREATED).body("City "+cityDTO.getName()+" created Successfully!");
            }
        } catch (CityValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return null;
    }

    @GetMapping("/cities")
    public ResponseEntity<List<City>> getAllCities(){
        return ResponseEntity.ok(cityService.getAllCities());
    }
    @GetMapping("/city/{identifier}")
    public ResponseEntity<Object> getCity(@PathVariable String identifier) throws CityNotFoundException {
        City city;
        try {
            int cityId = Integer.parseInt(identifier);
            city = cityService.getCityById(cityId);
            } catch (NumberFormatException e) {
                try{
                    city = cityService.getCityByName(identifier);
                } catch (CityNotFoundException e1) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e1.getMessage());
                }

            } catch (CityNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
        return ResponseEntity.status(HttpStatus.OK).body(city);
    }

    @DeleteMapping("/city/{identifier}")
    public ResponseEntity<String> deleteCity(@PathVariable String identifier) {
        try {
            int cityId = Integer.parseInt(identifier);
            if (cityService.deleteCityByID(cityId)) {
                return ResponseEntity.status(HttpStatus.OK).body("City deleted successfully.");
            }
        } catch (NumberFormatException e) {
            // If identifier is not an integer, attempt to delete by name
            try {
                if (cityService.deleteCityByName(identifier)) {
                    return ResponseEntity.status(HttpStatus.OK).body("City deleted successfully.");
                }
            } catch (CityValidationException ex) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
            }
        } catch (CityValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        // If we reach here, it means city was not found
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("City not found.");
    }

}

