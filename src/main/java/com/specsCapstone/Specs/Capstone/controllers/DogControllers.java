package com.specsCapstone.Specs.Capstone.controllers;

import com.specsCapstone.Specs.Capstone.dtos.DogDto;
import com.specsCapstone.Specs.Capstone.services.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dogs")
public class DogControllers {
    @Autowired
    private DogService dogService;

    @GetMapping("/users/{userId}")
    public List<DogDto> getAllDogsByUserId(@PathVariable Long userId){
        return dogService.getAllDogsByUserId(userId);
    }

    @DeleteMapping("/{dogId}")
    public void deleteDogById(@PathVariable Long dogId){
        dogService.deleteDogById(dogId);
    }

    @PostMapping("/users/{userId}")
    public void addDog(@RequestBody DogDto dogDto, @PathVariable Long userId){
        dogService.addDog(dogDto, userId);
    }

    @GetMapping("/{dogId}")
    public ResponseEntity<DogDto> getDogById(@PathVariable Long dogId){
        Optional<DogDto> dogDtoOptional = dogService.getDogById(dogId);
        return dogDtoOptional.map(dogDto -> ResponseEntity.ok().body(dogDto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
