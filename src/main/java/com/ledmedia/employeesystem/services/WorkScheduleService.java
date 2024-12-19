package com.ledmedia.employeesystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ledmedia.employeesystem.repositories.WorkScheduleRepository;

@Service
public class WorkScheduleService {
    @Autowired
    WorkScheduleRepository workScheduleRepository;
}
