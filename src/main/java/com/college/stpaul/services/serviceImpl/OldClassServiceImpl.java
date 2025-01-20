package com.college.stpaul.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.stpaul.entities.OldClass;
import com.college.stpaul.repository.OldClassRepo;
import com.college.stpaul.services.serviceInterface.OldClassService;

@Service
public class OldClassServiceImpl implements OldClassService{

    @Autowired
    private OldClassRepo oldClassRepo;

    @Override
    public OldClass addOldClass(OldClass oldClass) {
        return this.oldClassRepo.save(oldClass);
    }
    
}
