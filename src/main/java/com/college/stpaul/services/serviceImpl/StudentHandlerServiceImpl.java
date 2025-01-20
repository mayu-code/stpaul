package com.college.stpaul.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.stpaul.Helper.IncrementSession;
import com.college.stpaul.constants.Result;
import com.college.stpaul.entities.OldClass;
import com.college.stpaul.entities.Student;
import com.college.stpaul.services.serviceInterface.StudentHandelerService;

@Service
public class StudentHandlerServiceImpl implements StudentHandelerService{

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @Autowired
    private OldClassServiceImpl oldClassServiceImpl;


    @Override
    public void promotStudents(Long id) {
        Student student = new Student();
        student = this.studentServiceImpl.getStudentById(id);

        if(student.getCurrentClass().equalsIgnoreCase("12th")){
            student.setResult(Result.PASS);
            this.studentServiceImpl.addStudent(student);
            return;
        }
        else if(student.getCurrentClass().equalsIgnoreCase("11th")){
            OldClass oldClass = new OldClass();
            oldClass.setOldClass(student.getCurrentClass());
            oldClass.setSection(student.getSection());
            oldClass.setResult(Result.PASS);
            oldClass.setSession(student.getSession());
            oldClass.setRollNo(student.getRollNo());
            oldClass.setStudent(student);

            student.setCurrentClass("12th");
            student.setSection(null);
            student.setResult(Result.ON_GOING);
            student.setRollNo(null);
            student.setSession(IncrementSession.incrementSession(student.getSession()));
            this.oldClassServiceImpl.addOldClass(oldClass);
            this.studentServiceImpl.addStudent(student);
            return;
        }
    }
    
}
