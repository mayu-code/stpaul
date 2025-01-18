package com.college.stpaul.services.serviceInterface;

import java.util.List;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.STUnderline;

import com.college.stpaul.constants.Result;
import com.college.stpaul.entities.Student;

public interface StudentService {
    Student addStudent(Student student);
    List<Student> getStudentByField(String query,Result result,String currentClass,String session,int pageNo);
    Student getStudentById(long id);
    List<Student> getAllFailedStudent();
    long paginationData();
    List<Student> exportStudent(Result result,String currentClass,String session);
}
