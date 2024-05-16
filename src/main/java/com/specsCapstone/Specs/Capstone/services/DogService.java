package com.specsCapstone.Specs.Capstone.services;

import com.specsCapstone.Specs.Capstone.dtos.DogDto;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface DogService {
    @Transactional
    void addDog(DogDto dogDto, Long userId);

    @Transactional
    void deleteDogById(Long dogId);

    List<DogDto> getAllDogsByUserId(Long userId);

    @Transactional
    Optional<DogDto> getDogById(Long dogId);
}
