package com.college.stpaul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.stpaul.entities.BankDetails;

public interface BankDetailsRepo extends JpaRepository<BankDetails,Long>{
    
}
