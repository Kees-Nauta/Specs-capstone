package com.specsCapstone.Specs.Capstone.dtos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.specsCapstone.Specs.Capstone.entites.Appointment;
import com.specsCapstone.Specs.Capstone.entites.Dog;
import com.specsCapstone.Specs.Capstone.entites.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private Long id;
    private String username;
    private String password_hash;
    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private String address;
    private Boolean groomer;
    private Boolean admin;
    private Set<DogDto> dogDtoSet = new HashSet<>();

    public UserDto(User user) {
        if (user.getId() != null){
            this.id = user.getId();
        }
        if (user.getUsername() != null){
            this.username = user.getUsername();
        }
        if (user.getPassword_hash() != null){
            this.password_hash = user.getPassword_hash();
        }
        if (user.getFirst_name() != null){
            this.first_name = user.getFirst_name();
        }
        if (user.getLast_name() != null){
            this.last_name = user.getLast_name();
        }
        if (user.getEmail() != null){
            this.email = user.getEmail();
        }
        if (user.getPhone_number() != null){
            this.phone_number = user.getPhone_number();
        }
        if (user.getAddress() != null){
            this.address = user.getAddress();
        }

        this.groomer = user.getGroomer();

        this.admin = user.getAdmin();

    }
}
