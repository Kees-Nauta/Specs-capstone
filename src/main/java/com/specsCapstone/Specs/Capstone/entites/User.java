package com.specsCapstone.Specs.Capstone.entites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.specsCapstone.Specs.Capstone.dtos.AppointmentDto;
import com.specsCapstone.Specs.Capstone.dtos.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password_hash;

    @Column
    private String first_name;

    @Column
    private String last_name;

    @Column
    private String email;

    @Column
    private String phone_number;

    @Column
    private String address;

    @Column
    private Boolean groomer;

    @Column
    private Boolean admin;

    @OneToMany(mappedBy = "appointmentUser", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private Set<Appointment> appointmentSet = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private Set<Dog> dogSet = new HashSet<>();

    public User(UserDto userDto) {

        if (userDto.getUsername() != null){
            this.username = userDto.getUsername();
        }
        if (userDto.getPassword_hash() != null){
            this.password_hash = userDto.getPassword_hash();
        }
        if (userDto.getFirst_name() != null){
            this.first_name = userDto.getFirst_name();
        }
        if (userDto.getLast_name() != null){
            this.last_name = userDto.getLast_name();
        }
        if (userDto.getEmail() != null){
            this.email = userDto.getEmail();
        }
        if (userDto.getPhone_number() != null){
            this.phone_number = userDto.getPhone_number();
        }
        if (userDto.getAddress() != null){
            this.address = userDto.getAddress();
        }
        if (userDto.getGroomer() != null){
            this.groomer = userDto.getGroomer();
        }
        if (userDto.getAdmin() != null){
            this.admin = userDto.getAdmin();
        }
    }

}
