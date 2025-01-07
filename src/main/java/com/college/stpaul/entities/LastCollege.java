package com.college.stpaul.entities;

import com.college.stpaul.constants.Result;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class LastCollege {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String passedExam;
    private String collegeName;
    private String uDiseNO;
    private String lastStudentId;
    private String examination;
    private int rollNo;
    private String examMonth;
    private int marksObtained;
    private Result result;


    @OneToOne
    @JoinColumn(name = "student_id" , nullable = false)
    private Student student;

}   
