package com.college.stpaul.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.stpaul.entities.Student;
import com.college.stpaul.repository.StudentRepo;
import com.college.stpaul.services.serviceInterface.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public Student addStudent(Student student) {
        return this.studentRepo.save(student);
    }
    
}
