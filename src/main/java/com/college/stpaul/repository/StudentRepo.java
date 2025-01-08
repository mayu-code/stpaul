package com.college.stpaul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.stpaul.entities.Student;

public interface StudentRepo extends JpaRepository<Student,Long>{
    
}
