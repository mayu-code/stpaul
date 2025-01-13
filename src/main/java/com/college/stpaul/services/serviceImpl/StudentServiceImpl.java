package com.college.stpaul.services.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.college.stpaul.constants.Result;
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

    @Override
    public List<Student> getStudentByField(String query, Result result, String currentClass, String session) {
        Pageable pageable = PageRequest.of(0, 10);
        return this.studentRepo.searchStudents(query,result,currentClass,session,pageable);  
    }

    @Override
    public Student getStudentById(long id) {
        return this.studentRepo.findById(id).get();
    }

    @Override
    public List<Student> getAllFailedStudent() {
        Result result = Result.FAIL;
        return this.studentRepo.getFailedStudents(result);
    }
    
}
