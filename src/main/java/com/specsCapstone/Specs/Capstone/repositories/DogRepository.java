package com.specsCapstone.Specs.Capstone.repositories;

import com.specsCapstone.Specs.Capstone.entites.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {
}
