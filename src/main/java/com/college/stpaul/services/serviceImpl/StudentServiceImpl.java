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
        // if(query==null && result==null && currentClass==null && session==null){
        // }
        // else if(result==null && currentClass==null && session==null){
        //     return this.studentRepo.searchStudentByNameAndEmail(query, pageable);
        // }
        // else if(query==null && currentClass==null && session==null){
        //     return this.studentRepo.findStudentBasedONResult(result, pageable);
        // }
        // else if(query==null && result==null&& session==null){
        //     return this.studentRepo.findStudentBasedONClass(currentClass,pageable);
        // }
        // else if(currentClass==null && session==null){
        //     return this.studentRepo.searchStudentByNameAndEmailAndResult(query, result, pageable);
        // }
        // else if(result==null && session==null){
        //     return this.studentRepo.searchStudentByNameAndEmailAndClass(query,currentClass,pageable);
        // }
        // else if(result==null && currentClass==null){
        //     return this.studentRepo.searchStudentByNameAndEmailAndSession(query,session,pageable);
        // }
        // else if(query==null && session==null){
        //     return this.studentRepo.findStudentBasedONResultAndClass(result,currentClass,pageable);
        // }
        // else if(result==null && session==null){
        //     return this.studentRepo.findStudentBasedONResultAndSession(result,session,pageable);
        // }
        // else if(result==null && query==null){
        //     return this.studentRepo.findStudentBasedONClassAndSession(currentClass,session,pageable);
        // }
        // else if(session==null){
        //     return this.studentRepo.searchStudentByNameAndEmailAndResultAndClass(query,result,currentClass,pageable);
        // }
        // else if(currentClass==null){
        //     return this.studentRepo.searchStudentByNameAndEmailAndResultAndClass(query, result, session, pageable);
        // }
        // else if(query==null){
        //     return this.studentRepo.searchStudentByResultAndClassAndSession(result, currentClass, session, pageable);
        // } else {
        //     return this.studentRepo.searchStudentByNameAndEmailAndResultAndClassAndSession(query, result, currentClass, session, pageable);
        // }
    }

    @Override
    public Student getStudentById(long id) {
        return this.studentRepo.findById(id).get();
    }
    
}
