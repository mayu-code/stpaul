package com.college.stpaul.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.stpaul.entities.Receipt;
import com.college.stpaul.repository.ReceiptRepo;
import com.college.stpaul.services.serviceInterface.ReceiptService;



@Service
public class ReceiptServiceImpl implements ReceiptService{

    @Autowired
    private ReceiptRepo receiptRepo;

    @Override
    public Receipt addReceipt(Receipt receipt) {
        return this.receiptRepo.save(receipt);
    }
    
}
