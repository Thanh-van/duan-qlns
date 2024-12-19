package com.ledmedia.employeesystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ledmedia.employeesystem.models.OnLeave;
import com.ledmedia.employeesystem.repositories.OnLeaveRepository;

@Service
public class OnLeaveService {
    @Autowired OnLeaveRepository onLeaveRepository;
    public OnLeave save(OnLeave onLeave) {
        return onLeaveRepository.save(onLeave);
    }

    public void delete(OnLeave onLeave) {
        onLeaveRepository.delete(onLeave);
    }

    public void deleteById(long id) {
        onLeaveRepository.deleteById(id);
    }

    public List<OnLeave> findAll() {
        return onLeaveRepository.findAll();
    }

    public OnLeave findById(long id) {
        return onLeaveRepository.findById(id).orElse(null);
    }
}
