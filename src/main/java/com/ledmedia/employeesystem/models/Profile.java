package com.ledmedia.employeesystem.models;

import java.util.Date;

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
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String tel;
    private String email;
    private String gender;
    private String address;
    private String experience;
    private String academicLevel;
    private String CCCD;
    private String BHXH_number;
    private Date  join_date;
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    public String experienceShow() {
        switch (experience) {
            case "0":
                return "Dưới 1 năm";
            case "1":
                return "1 năm";
            case "2":
                return "2 năm";
            case "3":
                return "3 năm";
            case "4":
                return "4 năm";
            case "5":
                return "5 năm";
            case "6":
                return "6 năm";
            default:
                return "Không có kinh nghiệm";
        }
    }

    public String academicLevelShow() {
        switch (academicLevel) {
            case "TRUNG_HOC":
                return "Trung học phổ thông";
            case "CAO_DANG":
                return "Cao đẳng";
            case "DAI_HOC":
                return "Đại học";
            default:
                return "Không có học vấn";
        }
    }
}
