package com.college.stpaul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.college.stpaul.entities.PaymentDetails;

public interface PaymentDetailRepo extends JpaRepository<PaymentDetails,Long>{
    

    @Query("SELECT f FROM PaymentDetails f WHERE f.student.id=:id ")
    PaymentDetails findPaymentDetailsByStudentId(long id);
}
