package com.specsCapstone.Specs.Capstone.services;

import com.specsCapstone.Specs.Capstone.dtos.AppointmentDto;
import com.specsCapstone.Specs.Capstone.entites.Appointment;
import com.specsCapstone.Specs.Capstone.entites.Dog;
import com.specsCapstone.Specs.Capstone.entites.User;
import com.specsCapstone.Specs.Capstone.repositories.AppointmentRepository;
import com.specsCapstone.Specs.Capstone.repositories.DogRepository;
import com.specsCapstone.Specs.Capstone.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DogRepository dogRepository;


    @Override
    @Transactional
    public void addAppointment(AppointmentDto appointmentDto, Long dogId) {
        Optional<Dog> dogOptional = dogRepository.findById(dogId);
        Appointment appointment = new Appointment(appointmentDto);
        dogOptional.ifPresent(appointment::setDog);
        appointmentRepository.saveAndFlush(appointment);
    }


    @Override
    @Transactional
    public void deleteAppointmentById(Long appointmentId) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentId);
        appointmentOptional.ifPresent(appointment -> appointmentRepository.delete(appointment));
    }

    @Override
    @Transactional
    public void deleteAppointmentByDogId(Long dogId) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(dogId);
        appointmentOptional.ifPresent(appointment -> appointmentRepository.delete(appointment));
    }


    @Override
    @Transactional
    public  void updateAppointmentById(AppointmentDto appointmentDto) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentDto.getId());
        appointmentOptional.ifPresent(appointment -> {
            appointment.setTime(appointmentDto.getTime());
            appointment.setDate(appointmentDto.getDate());
            appointment.setService(appointmentDto.getService());
            appointment.setGroomerName(appointmentDto.getGroomerName());
            appointment.setComplete(appointmentDto.getComplete());
            appointmentRepository.saveAndFlush(appointment);
        });
    }



    @Override
    public List<AppointmentDto> getAllAppointmentsByDogId(Long dogId){
        Optional<Dog> dogOptional = dogRepository.findById(dogId);
        if (dogOptional.isPresent()){
            List<Appointment> appointmentList = appointmentRepository.findAllByDogEquals(dogOptional.get());
            return appointmentList.stream().map(appointment -> new AppointmentDto(appointment)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public List<AppointmentDto> getAllAppointmentsByGroomerName(String groomerName){
        Optional<User> userOptional = userRepository.findByUsername(groomerName);
        if (userOptional.isPresent()){
            List<Appointment> appointmentList = appointmentRepository.findAllByGroomerName(String.valueOf(userOptional.get()));
            return appointmentList.stream().map(appointment -> new AppointmentDto(appointment)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }


    @Override
    public Optional<AppointmentDto> getAppointmentById(Long appointmentId) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentId);
        if (appointmentOptional.isPresent()){
            return Optional.of(new AppointmentDto(appointmentOptional.get()));
        }
        return Optional.empty();
    }


}
