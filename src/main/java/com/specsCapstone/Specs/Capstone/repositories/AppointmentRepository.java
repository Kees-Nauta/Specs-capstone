package com.specsCapstone.Specs.Capstone.repositories;

import com.specsCapstone.Specs.Capstone.entites.Appointment;
import com.specsCapstone.Specs.Capstone.entites.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByDogEquals(Dog dog);
    List<Appointment> findAllByGroomerName(String groomerName);

}
