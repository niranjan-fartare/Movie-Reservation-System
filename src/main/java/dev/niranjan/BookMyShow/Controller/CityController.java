package dev.niranjan.BookMyShow.Controller;

import dev.niranjan.BookMyShow.DTO.CityDTO;
import dev.niranjan.BookMyShow.Model.City;
import dev.niranjan.BookMyShow.Service.CityService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CityController {
    @Autowired
    private CityService cityService;
    @PostMapping("/city")
    public ResponseEntity createCity(@RequestBody CityDTO cityDTO) {
        City savedCity = new City();
        try{
            String cityName = cityDTO.getName();
            if(cityName == null || cityName.isEmpty()) {
                throw new BadRequestException("City name cannot be empty");
            }
            savedCity = cityService.saveCity(cityName);

        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(savedCity);
    }

    @GetMapping("/city/{name}")
    public ResponseEntity getCity(@PathVariable String name) {
        return ResponseEntity.ok(cityService.getCityByName(name));
    }

    @DeleteMapping("/city/{id}")
    public ResponseEntity deleteCity(@PathVariable("id") int cityId) {
        return ResponseEntity.ok(cityService.deleteCity(cityId));
    }
}

