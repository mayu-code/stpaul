package com.college.stpaul.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.stpaul.entities.LastCollege;
import com.college.stpaul.repository.LastCollegeRepo;
import com.college.stpaul.services.serviceInterface.LastCollegeService;

@Service
public class LastCollegeServiceImpl implements LastCollegeService {

    @Autowired
    private LastCollegeRepo lastCollegeRepo;

    @Override
    public LastCollege addLastCollege(LastCollege lastCollege) {
        return this.lastCollegeRepo.save(lastCollege);
    }
    
}
