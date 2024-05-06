package com.specsCapstone.Specs.Capstone.controllers;

import com.specsCapstone.Specs.Capstone.dtos.AppointmentDto;
import com.specsCapstone.Specs.Capstone.dtos.DogDto;
import com.specsCapstone.Specs.Capstone.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentControllers {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/dogs/{dogId}")
    public void addAppointment(@RequestBody AppointmentDto appointmentDto, @PathVariable Long dogId){
        appointmentService.addAppointment(appointmentDto, dogId);
    }

    @DeleteMapping("/{appointmentId}")
    public void deleteAppointmentById(@PathVariable Long appointmentId){
        appointmentService.deleteAppointmentById(appointmentId);
    }

    @PutMapping
    public void updateAppointmentTimeById(@PathVariable AppointmentDto appointmentDto){
        appointmentService.updateAppointmentTimeById(appointmentDto);
    }

    @PutMapping
    public void updateAppointmentDateById(@PathVariable AppointmentDto appointmentDto){
        appointmentService.updateAppointmentDateById(appointmentDto);
    }

    @GetMapping("/{appointmentId}")
    public Optional<AppointmentDto> getAppointmentById(@PathVariable Long appointmentId){
        return appointmentService.getAppointmentById(appointmentId);
    }

    @GetMapping("/dogs/dogId")
    public List<AppointmentDto> getAllAppointmentsByDogId(Long dogId){
        return appointmentService.getAllAppointmentsByDogId(dogId);
    }

}
