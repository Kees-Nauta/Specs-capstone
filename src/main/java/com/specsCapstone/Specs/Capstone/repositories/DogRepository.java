package com.specsCapstone.Specs.Capstone.repositories;

import com.specsCapstone.Specs.Capstone.entites.Appointment;
import com.specsCapstone.Specs.Capstone.entites.Dog;
import com.specsCapstone.Specs.Capstone.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {
    List<Dog> findAllByUserEquals(User user);
}
