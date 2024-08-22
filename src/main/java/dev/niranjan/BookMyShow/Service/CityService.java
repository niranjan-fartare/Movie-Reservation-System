package dev.niranjan.BookMyShow.Service;

import dev.niranjan.BookMyShow.DTO.CityDTO;
import dev.niranjan.BookMyShow.Exception.CityNotFoundException;
import dev.niranjan.BookMyShow.Exception.CityValidationException;
import dev.niranjan.BookMyShow.Model.City;
import dev.niranjan.BookMyShow.Repository.CityRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityRepo cityRepo;
    @Transactional
    public boolean saveCity(CityDTO cityDTO) throws CityValidationException {
        City city = new City();
        if(cityDTO.getName() == null || cityDTO.getName().isEmpty()) {
            throw new CityValidationException("City Name is required");
        }
        if(cityRepo.existsByName(cityDTO.getName())){
            throw new CityValidationException("City "+cityDTO.getName()+" already exists");
        }

        city.setName(cityDTO.getName());
        city.setState(cityDTO.getState());
        cityRepo.save(city);
        return true;
    }
    @Transactional
    public City saveCity(City city) {
        cityRepo.save(city);
        return city;
    }
    public List<City> getAllCities(){
        return cityRepo.findAll();
    }

    public City getCityById(int cityId) throws CityNotFoundException {
        return cityRepo.findById(cityId).orElseThrow(() ->
                new CityNotFoundException("City with ID '" + cityId + "' does not exist"));
    }

    public City getCityByName(String name) throws CityNotFoundException {
        if (cityRepo.existsByName(name)) {
            return cityRepo.findByName(name);
        }
        throw new CityNotFoundException("City with name '" + name + "' does not exist");
    }
    @Transactional
    public boolean deleteCityByID(int cityId) throws CityValidationException {
        if (cityRepo.existsById(cityId)) {
            cityRepo.deleteById(cityId);
            return true;
        }
        throw new CityValidationException("City with ID '" + cityId + "' does not exist");
    }

    @Transactional
    public boolean deleteCityByName(String cityName) throws CityValidationException {
        if(cityRepo.existsByName(cityName)){
            cityRepo.deleteCityByName(cityName);
            return true;
        }
        throw new CityValidationException("City with name '" + cityName + "' does not exist");
    }
}
