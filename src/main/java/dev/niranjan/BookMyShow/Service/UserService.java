package dev.niranjan.BookMyShow.Service;

import dev.niranjan.BookMyShow.DTO.UserDTO;
import dev.niranjan.BookMyShow.Model.User;
import dev.niranjan.BookMyShow.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public boolean saveUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        userRepo.save(user);
        return true;
    }
}
