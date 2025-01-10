package com.college.stpaul.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.stpaul.entities.BioFocalSubject;
import com.college.stpaul.repository.BioFocalSubjectRepo;
import com.college.stpaul.services.serviceInterface.BioFocalSubjectService;

@Service
public class BioFocalSubjectServiceImpl implements BioFocalSubjectService{

    @Autowired
    private BioFocalSubjectRepo bioFocalSubjectRepo;

    @Override
    public BioFocalSubject addBioFocalSubject(BioFocalSubject bioFocalSubject) {
        return this.bioFocalSubjectRepo.save(bioFocalSubject);
    }
    
}
