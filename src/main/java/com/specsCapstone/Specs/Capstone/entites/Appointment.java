package com.specsCapstone.Specs.Capstone.entites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.specsCapstone.Specs.Capstone.dtos.AppointmentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Appointments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long time;

    @Column
    private String service;

    @Column
    private Boolean complete;

    @Column
    private Long groomer_id;

    @ManyToOne
    @JsonBackReference
    private User appointmentUser;

    @ManyToOne
    @JsonBackReference
    private Dog dog;

    public Appointment(AppointmentDto appointmentDto){

        if (appointmentDto.getTime() != null){
            this.time = appointmentDto.getTime();
        }
        if (appointmentDto.getService() != null){
            this.service = appointmentDto.getService();
        }
        if (appointmentDto.getComplete() != null){
            this.complete = appointmentDto.getComplete();
        }
    }

}