package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import webdev.models.User;
import webdev.repositories.UserRepository;

@RestController
public class UserService {
    @Autowired
    UserRepository userRepository;

    @PostMapping
    public User createUser(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @GetMapping("/api/user")
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/api/user/{userId}")
    public User findUserById(@PathVariable("userId") int userId) {
        return userRepository.findById(userId).get(0);
    }

    @DeleteMapping("/api/user/{userId}")
    public void deleteUser(@PathVariable("userId") int userId) {
        User user = userRepository.findById(userId).get(0);
        userRepository.delete(user);
    }
}
