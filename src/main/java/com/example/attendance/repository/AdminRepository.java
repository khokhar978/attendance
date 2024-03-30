package com.example.attendance.repository;

import com.example.attendance.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    // Add custom query methods if needed
}
