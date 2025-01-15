package com.college.stpaul.services.serviceInterface;

import java.util.List;

import com.college.stpaul.constants.Result;
import com.college.stpaul.entities.Student;

public interface StudentService {
    Student addStudent(Student student);
    List<Student> getStudentByField(String query,Result result,String currentClass,String session,int pageNo);
    Student getStudentById(long id);
    List<Student> getAllFailedStudent();
    long paginationData();
}
