package com.college.stpaul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.college.stpaul.entities.Receipt;

public interface ReceiptRepo extends JpaRepository<Receipt,Long>{

    @Query("SELECT r FROM Receipt r WHERE r.paymentDetails.id =:id")
    Receipt findByPaymentDetailsId(long id);
}
