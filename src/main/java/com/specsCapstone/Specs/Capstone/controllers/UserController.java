package com.specsCapstone.Specs.Capstone.controllers;

import com.specsCapstone.Specs.Capstone.dtos.UserDto;
import com.specsCapstone.Specs.Capstone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public List<String> addUser(@RequestBody UserDto userDto){
        String passHash = passwordEncoder.encode(userDto.getPassword_hash());
        userDto.setPassword_hash(passHash);
        return userService.addUser(userDto);
    }

    @PostMapping("/login")
    public List<String> userLogin(@RequestBody UserDto userDto){
        return userService.userLogin(userDto);
    }
}


