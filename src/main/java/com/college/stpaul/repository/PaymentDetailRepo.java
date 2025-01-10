package com.college.stpaul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.stpaul.entities.PaymentDetails;

public interface PaymentDetailRepo extends JpaRepository<PaymentDetails,Long>{
    
}
