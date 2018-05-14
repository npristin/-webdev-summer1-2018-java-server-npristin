package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import webdev.models.User;
import webdev.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class UserService {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/api/user")
    public User createUser(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @GetMapping("/api/user")
    public List<User> findAllUsers(@RequestParam(name="username", required = false) String username) {
        if (username != null) {
            return (List<User>) userRepository.findUserByUsername(username);
        }
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/api/user/{userId}")
    public User findUserById(@PathVariable("userId") int userId) {
        Optional<User> potentialUser = userRepository.findById(userId);
        if (potentialUser.isPresent()) {
            return potentialUser.get();
        }
        return null;
    }

    @PutMapping("/api/user/{userId}")
    public User updateUser(@RequestBody User newUser, @PathVariable("userId") int userId) {
        Optional<User> potentialUser = userRepository.findById(userId);
        if (potentialUser.isPresent()) {
            User user = potentialUser.get();
            user.setUsername(newUser.getUsername());
            user.setPassword(newUser.getPassword());
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setEmail(newUser.getEmail());
            user.setPhone(newUser.getPhone());
            user.setRole(newUser.getRole());
            user.setDateOfBirth(newUser.getDateOfBirth());
            userRepository.save(user);
            return user;
        }
        return null;
    }

    @DeleteMapping("/api/user/{userId}")
    public void deleteUser(@PathVariable("userId") int userId) {
        userRepository.deleteById(userId);
    }


    @PostMapping("/api/register")
    public User register(@RequestBody User user, HttpServletRequest request) {
        List<User> newUser = (List<User>) userRepository.findUserByUsername(user.getUsername());
        if (newUser.size() == 0) {
            userRepository.save(user);
            request.getServletContext().setAttribute("user", user);
            return user;
        }
        return null;
    }

    @PostMapping("/api/login")
    public User login(@RequestBody User user, HttpServletRequest request) {
        String username = user.getUsername();
        String password = user.getPassword();

        List<User> potentialuser = (List<User>) userRepository.findUserByCredentials(username, password);

        if (potentialuser.size() != 0) {
            request.getServletContext().setAttribute("user", potentialuser.get(0));
            return potentialuser.get(0);
        }
        return null;
    }

    @PutMapping("/api/profile")
    public User updateProfile(@RequestBody User user, HttpServletRequest request) {
        User sessionUser = (User) request.getServletContext().getAttribute("user");
        if (sessionUser != null) {
            sessionUser.setPhone(user.getPhone());
            sessionUser.setEmail(user.getEmail());
            sessionUser.setRole(user.getRole());
            sessionUser.setDateOfBirth(user.getDateOfBirth());

            userRepository.save(sessionUser);
            return sessionUser;
        }
        return null;
    }

    @PostMapping("/api/logout")
    public void logout(HttpSession session) {
        session.invalidate();
        return;
    }
}
