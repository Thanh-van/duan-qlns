package com.ledmedia.employeesystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ledmedia.employeesystem.models.PayWageLog;
import com.ledmedia.employeesystem.repositories.PayWaveLogRepository;

@Service
public class PayWaveLogService {
        @Autowired PayWaveLogRepository payWaveLogRepository;
    public PayWageLog save(PayWageLog payWageLog) {
        return payWaveLogRepository.save(payWageLog);
    }

    public void delete(PayWageLog payWageLog) {
        payWaveLogRepository.delete(payWageLog);
    }

    public void deleteById(long id) {
        payWaveLogRepository.deleteById(id);
    }

    public List<PayWageLog> findAll() {
        return payWaveLogRepository.findAll();
    }

    public PayWageLog findById(long id) {
        return payWaveLogRepository.findById(id).orElse(null);
    }
}
