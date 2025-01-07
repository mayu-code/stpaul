package com.college.stpaul.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String firstName;
    private String fatherName;
    private String motherName;
    private String sirName;
    private String email;
    private String phoneNo;
    private String gender;
    private String caste;
    private String category;
    private String scholershipCategory;
    private String localAddress;
    private String permanentAddress;
    private LocalDate DOB;
    private String adharNO;
    private String bloodGroup;


    @OneToOne
    @JoinColumn(name = "admissionForm_id", nullable = false)
    private AdmissionForm admissionForm;

}
