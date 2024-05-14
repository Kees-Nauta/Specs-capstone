package com.specsCapstone.Specs.Capstone.services;

import com.specsCapstone.Specs.Capstone.dtos.UserDto;
import com.specsCapstone.Specs.Capstone.entites.User;
import com.specsCapstone.Specs.Capstone.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public List<String> addUser(UserDto userDto){
        List<String> response = new ArrayList<>();
        User user = new User(userDto);
        userRepository.saveAndFlush(user);
        response.add("http://localhost:8080/login.html");
        return response;
    }

    @Override
    public List<String> userLogin(UserDto userDto){
        List<String> response = new ArrayList<>();
        Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername());
        if (userOptional.isPresent()){
            if (passwordEncoder.matches(userDto.getPassword_hash(), userOptional.get().getPassword_hash())){
                response.add("http://localhost:8080/home.html");
                response.add(String.valueOf(userOptional.get().getId()));
            } else {
                response.add("username or password incorrect");
            }
        } else {
            response.add("username or password incorrect");
        }
        return response;
    }

    @Override
    public boolean checkAdminStatus(Long userId) {
        return false;
    }

    @Override
    public Optional<UserDto> getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.map(UserDto::new);
    }

}
