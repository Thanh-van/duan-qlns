package com.ledmedia.employeesystem.models;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "staffs")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String username;
    private String password;
    private long salary;
    private String position; // STAFF|LEADER|ADMIN
    private long baseSalary;

    @OneToOne(mappedBy = "staff")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "staff")
    private List<TimeSheet> timeSheets;

    @OneToMany(mappedBy = "staff", fetch = FetchType.EAGER)
    private List<Discipline> disciplines;

    @OneToMany(mappedBy = "staff", fetch = FetchType.EAGER)
    private List<Felicitation> felicitations;

    @OneToMany(mappedBy = "staff")
    private List<OnLeave> onLeaves;

    @OneToMany(mappedBy = "staff")
    private List<PayWageLog> payWageLogs;

    
}
