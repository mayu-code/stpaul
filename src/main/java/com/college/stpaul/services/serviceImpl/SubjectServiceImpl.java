package com.college.stpaul.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.stpaul.entities.Subject;
import com.college.stpaul.repository.SubjectRepo;
import com.college.stpaul.services.serviceInterface.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService{

    @Autowired
    private SubjectRepo subjectRepo;

    @Override
    public Subject addSubject(Subject subject) {
        return this.subjectRepo.save(subject);
    }
    
}
