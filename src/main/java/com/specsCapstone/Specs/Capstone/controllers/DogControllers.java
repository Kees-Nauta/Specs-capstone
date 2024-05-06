package com.specsCapstone.Specs.Capstone.controllers;

import com.specsCapstone.Specs.Capstone.dtos.DogDto;
import com.specsCapstone.Specs.Capstone.services.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dogs")
public class DogControllers {
    @Autowired
    private DogService dogService;

    @GetMapping("/user/{userId}")
    public List<DogDto> getAllDogsByUserId(@PathVariable Long userId){
        return dogService.getAllDogsByUserId(userId);
    }

    @DeleteMapping("/{dogId}")
    public void deleteDogById(@PathVariable Long dogId){
        dogService.deleteDogById(dogId);
    }

    @PostMapping("/user/{userId}")
    public void addDog(@RequestBody DogDto dogDto, @PathVariable Long userId){
        dogService.addDog(dogDto, userId);
    }

}
