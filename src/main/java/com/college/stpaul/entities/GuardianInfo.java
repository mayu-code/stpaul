package com.college.stpaul.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class GuardianInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String guardianName;
    private String guardianRelation;
    private String guardianPhoneNo;
    private String guardianOccupation;
    private String guardianIncome;

    @OneToOne
    @JoinColumn(name = "student_id",nullable = false)
    private Student student;
}
