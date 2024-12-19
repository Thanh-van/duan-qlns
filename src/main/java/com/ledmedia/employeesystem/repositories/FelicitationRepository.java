package com.ledmedia.employeesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ledmedia.employeesystem.models.Felicitation;

@Repository
public interface FelicitationRepository extends JpaRepository<Felicitation, Long> {
    
}
