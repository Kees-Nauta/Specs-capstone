package com.specsCapstone.Specs.Capstone.services;

import com.specsCapstone.Specs.Capstone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    @Autowired
    private UserRepository userRepository;
}
