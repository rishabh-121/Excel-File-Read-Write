package com.personal.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.project.entity.EmployeeData;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeData, Long> {

}
