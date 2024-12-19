package com.ledmedia.employeesystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ledmedia.employeesystem.models.Discipline;
import com.ledmedia.employeesystem.repositories.DisciplineRepository;

@Service
public class DisciplineService {
    @Autowired DisciplineRepository disciplineRepository;
    public Discipline save(Discipline discipline) {
        return disciplineRepository.save(discipline);
    }

    public void delete(Discipline discipline) {
        disciplineRepository.delete(discipline);
    }

    public void deleteById(long id) {
        disciplineRepository.deleteById(id);
    }

    public List<Discipline> findAll() {
        return disciplineRepository.findAll();
    }

    public Discipline findById(long id) {
        return disciplineRepository.findById(id).orElse(null);
    }
}
