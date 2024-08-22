package dev.niranjan.BookMyShow.Exception;

public class CityNotFoundException extends Exception{
    public CityNotFoundException(String city) {
        super(city);
    }
}
