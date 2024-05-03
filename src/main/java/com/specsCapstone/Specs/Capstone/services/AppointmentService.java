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
    void updateAppointmentTimeById(AppointmentDto appointmentDto);

    List<AppointmentDto> getAllAppointmentsByDogId(Long dogId);

    Optional<AppointmentDto> getAppointmentById(Long appointmentId);
}
