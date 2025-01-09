package com.college.stpaul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.stpaul.entities.Documents;

public interface DocumentsRepo extends JpaRepository<Documents,Long>{
    
}
