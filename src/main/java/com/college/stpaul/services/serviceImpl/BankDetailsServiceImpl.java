package com.college.stpaul.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.stpaul.entities.BankDetails;
import com.college.stpaul.repository.BankDetailsRepo;
import com.college.stpaul.services.serviceInterface.BankDetailsService;

@Service
public class BankDetailsServiceImpl implements BankDetailsService {

    @Autowired
    private BankDetailsRepo bankDetailsRepo;

    @Override
    public BankDetails addBankDetails(BankDetails bankDetails) {
        return this.bankDetailsRepo.save(bankDetails);
    }
    
}
