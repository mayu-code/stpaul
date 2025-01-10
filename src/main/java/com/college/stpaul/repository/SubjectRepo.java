package com.college.stpaul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.stpaul.entities.Subject;

public interface SubjectRepo extends JpaRepository<Subject,Long> {
    
}
