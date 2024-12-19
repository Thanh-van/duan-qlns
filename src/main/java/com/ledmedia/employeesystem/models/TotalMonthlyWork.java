package com.ledmedia.employeesystem.models;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "totalmonthlywork")
public class TotalMonthlyWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date month;
    @OneToMany(mappedBy = "totalMonthlyWork", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TimeSheet> timeSheets;
    @ManyToOne
    @JoinColumn(name = "processed_by_id")
    private Staff processedBy;
    @OneToOne(mappedBy = "totalMonthlyWork")
    private PayWageLog payWageLogs;
    @ManyToOne
	@JoinColumn(name = "staff_id")
	private Staff staff;

}
