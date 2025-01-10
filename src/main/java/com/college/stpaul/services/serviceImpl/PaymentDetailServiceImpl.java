package com.college.stpaul.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.stpaul.entities.PaymentDetails;
import com.college.stpaul.repository.PaymentDetailRepo;
import com.college.stpaul.services.serviceInterface.PaymentDetailService;

@Service
public class PaymentDetailServiceImpl implements PaymentDetailService {

    @Autowired
    private PaymentDetailRepo paymentDetailRepo;

    @Override
    public PaymentDetails addPaymentDetails(PaymentDetails paymentDetails) {
        return this.paymentDetailRepo.save(paymentDetails);
    }
    
}
