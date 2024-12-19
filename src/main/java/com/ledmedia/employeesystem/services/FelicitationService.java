package com.ledmedia.employeesystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ledmedia.employeesystem.models.Felicitation;
import com.ledmedia.employeesystem.repositories.FelicitationRepository;

@Service
public class FelicitationService {
    @Autowired FelicitationRepository felicitationRepository;
    public Felicitation save(Felicitation felicitation) {
        return felicitationRepository.save(felicitation);
    }

    public void delete(Felicitation felicitation) {
        felicitationRepository.delete(felicitation);
    }

    public void deleteById(long id) {
        felicitationRepository.deleteById(id);
    }

    public List<Felicitation> findAll() {
        return felicitationRepository.findAll();
    }

    public Felicitation findById(long id) {
        return felicitationRepository.findById(id).orElse(null);
    }
}
