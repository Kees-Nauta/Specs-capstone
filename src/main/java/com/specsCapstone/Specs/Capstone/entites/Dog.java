package com.specsCapstone.Specs.Capstone.entites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.specsCapstone.Specs.Capstone.dtos.DogDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Dogs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String dog_name;

    @Column
    private String breed;

    @Column
    private String weight;

    @ManyToOne
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "dog", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private Set<Appointment> appointmentSet = new HashSet<>();

    public Dog(DogDto dogDto){

        if (dogDto.getDog_name() != null){
            this.dog_name = dogDto.getDog_name();
        }
        if (dogDto.getBreed() != null){
            this.breed = dogDto.getBreed();
        }
        if (dogDto.getWeight() != null){
            this.weight = dogDto.getWeight();
        }
    }


}
