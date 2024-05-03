package com.specsCapstone.Specs.Capstone.services;

import com.specsCapstone.Specs.Capstone.dtos.AppointmentDto;
import com.specsCapstone.Specs.Capstone.dtos.DogDto;
import com.specsCapstone.Specs.Capstone.entites.Appointment;
import com.specsCapstone.Specs.Capstone.entites.Dog;
import com.specsCapstone.Specs.Capstone.entites.User;
import com.specsCapstone.Specs.Capstone.repositories.AppointmentRepository;
import com.specsCapstone.Specs.Capstone.repositories.DogRepository;
import com.specsCapstone.Specs.Capstone.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DogServiceImpl implements DogService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DogRepository dogRepository;


    @Override
    @Transactional
    public void addDog(DogDto dogDto, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Dog dog = new Dog(dogDto);
        userOptional.ifPresent(dog::setUser);
        dogRepository.saveAndFlush(dog);
    }

    @Override
    @Transactional
    public void deleteDogById(Long dogId) {
        Optional<Dog> dogOptional = dogRepository.findById(dogId);
        dogOptional.ifPresent(dog -> dogRepository.delete(dog));
    }

    @Override
    public List<DogDto> getAllDogsByUserId(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()){
            List<Dog> dogList = dogRepository.findAllByUserEquals(userOptional.get());
            return dogList.stream().map(dog -> new DogDto(dog)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

}
