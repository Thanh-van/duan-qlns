package com.ledmedia.employeesystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ledmedia.employeesystem.models.Department;
import com.ledmedia.employeesystem.repositories.DepartmentRepository;

@Service
public class DepartmentService {
    @Autowired DepartmentRepository departmentRepository;
    
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    public void delete(Department department) {
        departmentRepository.delete(department);
    }

    public void deleteById(long id) {
        departmentRepository.deleteById(id);
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department findById(long id) {
        return departmentRepository.findById(id).orElse(null);
    }
}
