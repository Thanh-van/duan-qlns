package com.ledmedia.employeesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ledmedia.employeesystem.models.TimeSheet;

@Repository
public interface TimeSheetRepository extends JpaRepository<TimeSheet, Long>{
    @Modifying
    @Query("DELETE FROM TimeSheet o WHERE o.staff.id = :id")
    public int deleteByUserId(@Param(value = "id") long id);
}
