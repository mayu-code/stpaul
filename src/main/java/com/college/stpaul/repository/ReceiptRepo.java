package com.college.stpaul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.stpaul.entities.Receipt;

public interface ReceiptRepo extends JpaRepository<Receipt,Long>{
    
}
