package com.ledmedia.employeesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ledmedia.employeesystem.models.TotalMonthlyWork;

public interface TotalMonthlyWorkRepository  extends JpaRepository<TotalMonthlyWork,Long>{
    
}
