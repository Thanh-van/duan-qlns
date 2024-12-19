package com.ledmedia.employeesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ledmedia.employeesystem.models.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
