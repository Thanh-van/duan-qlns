package com.ledmedia.employeesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ledmedia.employeesystem.models.RestoreAttendance;

@Repository
public interface RestoreAttendanceRepository extends JpaRepository<RestoreAttendance, Long>{
    
}
