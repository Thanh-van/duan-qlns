package com.ledmedia.employeesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ledmedia.employeesystem.models.Discipline;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Long>{

}