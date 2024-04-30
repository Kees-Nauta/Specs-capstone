package com.specsCapstone.Specs.Capstone.repositories;

import com.specsCapstone.Specs.Capstone.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
