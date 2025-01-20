package com.college.stpaul.entities;

import com.college.stpaul.constants.Result;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Entity
@Data
public class OldClass {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
   
    private String oldClass;
    private String section;
    private Result result=Result.ON_GOING;
    private String rollNo;
    private String session;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @JsonIgnore
    private Student student;
}
