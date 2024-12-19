package com.ledmedia.employeesystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ledmedia.employeesystem.models.Profile;
import com.ledmedia.employeesystem.repositories.ProfileRepository;

@Service
public class ProfileService {
    @Autowired ProfileRepository profileRepository;
    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    public void delete(Profile felicitation) {
        profileRepository.delete(felicitation);
    }

    public void deleteById(long id) {
        profileRepository.deleteById(id);
    }

    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    public Profile findById(long id) {
        return profileRepository.findById(id).orElse(null);
    }
}
