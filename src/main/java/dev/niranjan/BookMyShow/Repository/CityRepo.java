package dev.niranjan.BookMyShow.Repository;

import dev.niranjan.BookMyShow.Model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepo extends JpaRepository<City, Integer> {
    City findByName(String cityName);
    void deleteCityByName(String cityName);
    boolean existsByName(String cityName);
}
