package com.specsCapstone.Specs.Capstone.services;

import com.specsCapstone.Specs.Capstone.dtos.AppointmentDto;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    @Transactional
    void addAppointment(AppointmentDto appointmentDto, Long dogId);

    @Transactional
    void deleteAppointmentById(Long appointmentId);

    @Transactional
    void deleteAppointmentByDogId(Long dogId);

    @Transactional
    void updateAppointmentById(AppointmentDto appointmentDto);

   

    List<AppointmentDto> getAllAppointmentsByDogId(Long dogId);

    List<AppointmentDto> getAllAppointmentsByGroomerName(String groomerName);

    Optional<AppointmentDto> getAppointmentById(Long appointmentId);
}
