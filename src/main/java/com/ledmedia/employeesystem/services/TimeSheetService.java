package com.ledmedia.employeesystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ledmedia.employeesystem.models.TimeSheet;
import com.ledmedia.employeesystem.repositories.TimeSheetRepository;

@Service
public class TimeSheetService {
    @Autowired TimeSheetRepository timeSheetRepository;
    public TimeSheet save(TimeSheet timeSheet) {
        return timeSheetRepository.save(timeSheet);
    }

    public void delete(TimeSheet timeSheet) {
        timeSheetRepository.delete(timeSheet);
    }

    public void deleteById(long id) {
        timeSheetRepository.deleteById(id);
    }
    @Transactional
    public void deleteByUserId(long id) {
        timeSheetRepository.deleteByUserId(id);
    }

    public List<TimeSheet> findAll() {
        return timeSheetRepository.findAll();
    }

    public TimeSheet findById(long id) {
        return timeSheetRepository.findById(id).orElse(null);
    }
}
