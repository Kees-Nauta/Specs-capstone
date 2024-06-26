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

    @DeleteMapping("/dogs/{dogId}")
    public void deleteAppointmentByDogId(@PathVariable Long dogId){
        appointmentService.deleteAppointmentByDogId(dogId);
    }

    @PutMapping("/{appointmentId}")
    public void updateAppointmentById(@RequestBody AppointmentDto appointmentDto){
        appointmentService.updateAppointmentById(appointmentDto);
    }

    @GetMapping("/{appointmentId}")
    public Optional<AppointmentDto> getAppointmentById(@PathVariable Long appointmentId){
        return appointmentService.getAppointmentById(appointmentId);
    }

    @GetMapping("/groomers/{groomerName}")
    public List<AppointmentDto> getAllAppointmentsByGroomerName(@PathVariable Long groomerName){
        return appointmentService.getAllAppointmentsByGroomerName(String.valueOf(groomerName));
    }

    @GetMapping("/dogs/{dogId}")
    public List<AppointmentDto> getAllAppointmentsByDogId(@PathVariable Long dogId){
        return appointmentService.getAllAppointmentsByDogId(dogId);
    }

    @GetMapping("/all")
    public List<AppointmentDto> getAllAppointments(){
        return appointmentService.getAllAppointments();
    }

}
