package com.college.stpaul.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.stpaul.entities.Subjects;
import com.college.stpaul.repository.SubjectsRepo;
import com.college.stpaul.services.serviceInterface.SubjectsService;

@Service
public class SubjectsServiceImpl implements SubjectsService  {

    @Autowired
    private SubjectsRepo subjectsRepo;

    @Override
    public Subjects addSubjects(Subjects subjects) {
        return this.subjectsRepo.save(subjects);
    }
    
}
