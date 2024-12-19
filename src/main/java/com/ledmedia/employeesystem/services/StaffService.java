package com.ledmedia.employeesystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ledmedia.employeesystem.models.Staff;
import com.ledmedia.employeesystem.repositories.StaffRepository;

@Service
public class StaffService {
    @Autowired StaffRepository staffRepository;

    public Staff save(Staff staff) {
        return staffRepository.save(staff);
    }

    public void delete(Staff staff) {
        staffRepository.delete(staff);
    }

    public void deleteById(long id) {
        staffRepository.deleteById(id);
    }

    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    public Staff findById(long id) {
        return staffRepository.findById(id).orElse(null);
    }

    public Staff findByUsername(String username) {
        return staffRepository.findByUsername(username);
    }

    public boolean isExists(String username, String password) {
        if(staffRepository.findByUsername(username) == null) {
            return false;
        } else {
            return staffRepository.findByUsername(username).getPassword().equals(password);
        }
        
    }
}
