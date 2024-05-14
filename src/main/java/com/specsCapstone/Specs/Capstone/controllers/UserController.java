package com.specsCapstone.Specs.Capstone.controllers;

import com.specsCapstone.Specs.Capstone.dtos.UserDto;
import com.specsCapstone.Specs.Capstone.entites.User;
import com.specsCapstone.Specs.Capstone.repositories.UserRepository;
import com.specsCapstone.Specs.Capstone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public List<String> addUser(@RequestBody UserDto userDto) {
        String passHash = passwordEncoder.encode(userDto.getPassword_hash());
        userDto.setPassword_hash(passHash);
        return userService.addUser(userDto);
    }

    @PostMapping("/login")
    public List<String> userLogin(@RequestBody UserDto userDto) {
        return userService.userLogin(userDto);
    }

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return new UserDto(user);
        }
        return null;
    }
}