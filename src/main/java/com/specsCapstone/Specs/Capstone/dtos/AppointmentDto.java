package com.specsCapstone.Specs.Capstone.dtos;


import com.specsCapstone.Specs.Capstone.entites.Appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto implements Serializable {
    private Long id;
    private Long time;
    private String service;
    private Boolean complete;
    private Long groomer_id;
    private DogDto dogDto;
    private UserDto userDto;

    public AppointmentDto(Appointment appointment){
        if (appointment.getId() != null){
            this.id = appointment.getId();
        }
        if (appointment.getTime() != null){
            this.time = appointment.getTime();
        }
        if (appointment.getService() != null){
            this.service = appointment.getService();
        }
        if (appointment.getComplete() != null){
            this.complete = appointment.getComplete();
        }
    }
}
