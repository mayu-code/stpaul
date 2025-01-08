package com.college.stpaul.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.stpaul.entities.GuardianInfo;
import com.college.stpaul.repository.GuardianInfoRepo;
import com.college.stpaul.services.serviceInterface.GuardianInfoService;

@Service
public class GuardianInfoServiceImpl implements GuardianInfoService{

    @Autowired
    private GuardianInfoRepo guardianInfoRepo;

    @Override
    public GuardianInfo addGuardianInfo(GuardianInfo guardianInfo) {
        return this.guardianInfoRepo.save(guardianInfo);
    }
    
}
