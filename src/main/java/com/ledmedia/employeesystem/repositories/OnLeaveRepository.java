package com.ledmedia.employeesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ledmedia.employeesystem.models.OnLeave;

public interface OnLeaveRepository extends JpaRepository<OnLeave, Long> {
    
}
