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
@Table(name = "pay_wage_logs")
public class PayWageLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date month;
    private double baseSalary;
    private double bonuses;
    private double bonusAmount;
    private double penaltyAmount;
    private double totalSalary;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;
    @ManyToOne
	@JoinColumn(name = "total_monthly_work_id")
	private  TotalMonthlyWork totalMonthlyWork; // Sửa lại tên entity

        public void calculateBaseSalaryFromAccount() {
            // Kiểm tra xem nhân viên có tồn tại không
            if (staff != null) {
                // Lấy thông tin về lương từ tài khoản nhân viên và gán cho baseSalary
                this.baseSalary = staff.getSalary();
            } else {
                // Xử lý khi không tìm thấy nhân viên
                System.out.println("Không tìm thấy nhân viên");
            }
        }
}
