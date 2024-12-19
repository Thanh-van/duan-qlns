package com.ledmedia.employeesystem.models;

import java.sql.Date;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "workschedules")
public class WorkSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date date;
    private String shifts;
    private String starttime;
    private String endtime;
    @ManyToOne
    @JoinColumn(name = "processed_by_id")
    private Staff processedBy;
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    public String shiftsShow() {
        switch (shifts) {
            case "0":
                return "CA1";
            case "1":
                return "CA2";
            default:
                return "CA3";

        }
    }
}
