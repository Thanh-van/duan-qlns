package com.ledmedia.employeesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ledmedia.employeesystem.models.WorkSchedule;
@Repository
public interface WorkScheduleRepository  extends JpaRepository<WorkSchedule, Long>{
    
}
