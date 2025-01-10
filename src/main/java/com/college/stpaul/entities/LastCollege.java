package com.college.stpaul.entities;

import com.college.stpaul.constants.Result;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private String collegeName;
    private String udiseNo;
    private String lastStudentId;
    private String examination;
    private String rollNo;
    private String examMonth;
    private int marksObtained;
    private Result result;
    private boolean isAtkt;


    @OneToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;

}   
