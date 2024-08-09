package dev.niranjan.BookMyShow.Service;

import dev.niranjan.BookMyShow.Model.City;
import dev.niranjan.BookMyShow.Repository.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    @Autowired
    private CityRepo cityRepo;

    public City saveCity(String name) {
        City city = new City();
        city.setName(name);
        return cityRepo.save(city);
    }

    public boolean deleteCity(int id) {
        cityRepo.deleteById(id);
        return true;
    }

    public City getCityByName(String name) {
        return cityRepo.findByName(name);
    }
}
