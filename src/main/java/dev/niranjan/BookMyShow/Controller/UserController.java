package dev.niranjan.BookMyShow.Controller;

import dev.niranjan.BookMyShow.DTO.UserDTO;
import dev.niranjan.BookMyShow.Model.User;
import dev.niranjan.BookMyShow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<Object> saveUser(@RequestBody UserDTO userDTO) throws Exception {

        try{
            userService.saveUser(userDTO);
        } catch (Exception e) {
            throw  new Exception(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
}
