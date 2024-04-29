package com.specsCapstone.Specs.Capstone.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.specsCapstone.Specs.Capstone.entites.Appointment;
import com.specsCapstone.Specs.Capstone.entites.Dog;
import com.specsCapstone.Specs.Capstone.entites.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DogDto implements Serializable {
    private Long id;
    private String dog_name;
    private String breed;
    private String weight;
    private UserDto userDto;
    private Set<Appointment> appointmentSet = new HashSet<>();

    public DogDto(Dog dog){
        if (dog.getId() != null){
            this.id = dog.getId();
        }
        if (dog.getDog_name() != null){
            this.dog_name = dog.getDog_name();
        }
        if (dog.getBreed() != null){
            this.breed = dog.getBreed();
        }
        if (dog.getWeight() != null){
            this.weight = dog.getWeight();
        }
    }
}
