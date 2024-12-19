package com.ledmedia.employeesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ledmedia.employeesystem.models.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    @Query("select o from Staff o where o.username = :username")
    public Staff findByUsername(@Param("username") String username);
}
