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
    public Object outputStream;

    @Override
    public Student addStudent(Student student) {
        return this.studentRepo.save(student);
    }

    @Override
    public List<Student> getStudentByField(String query, Result result, String currentClass, String session,String section,int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 10);
        return this.studentRepo.searchStudents(query,result,currentClass,session,section,pageable);  
    }

    @Override
    public Student getStudentById(long id) {
        return this.studentRepo.findById(id).get();
    }

    @Override
    public List<Student> getAllFailedStudent(String query,String currentClass,String session,String section,int pageNo) {
        Result result = Result.FAIL;
        Pageable pageable = PageRequest.of(pageNo, 10);
        return this.studentRepo.getFailedStudents(result,query,currentClass,session,section,pageable);
    }

    @Override
    public long paginationData() {
        return this.studentRepo.countAllStudents();
    }

    @Override
    public List<Student> exportStudent(Result result, String currentClass, String session,String section) {
        return this.studentRepo.exportStudents(result, currentClass, session,section);
    }

    
}
