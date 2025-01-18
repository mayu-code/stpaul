package com.college.stpaul.entities;

import java.time.LocalDate;
import java.util.List;

import com.college.stpaul.constants.Result;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
    private String surname;
    private String email;
    private String phoneNo;
    private String gender;
    private String caste;
    private String category;
    private String scholarshipCategory;
    private String localAddress;
    private String permanentAddress;
    private String dob;
    private String adharNo;
    private String bloodGroup;
    private String currentClass;
    private String section;
    private Result result=Result.ON_GOING;
    private String rollNo;
    private String session;

    @Column(columnDefinition = "LONGTEXT")
    private String image;


    @OneToOne
    @JoinColumn(name = "admissionForm_id", nullable = false)
    @JsonIgnore
    private AdmissionForm admissionForm;


    @OneToOne(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private BankDetails bankDetails;

    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Documents> documents;

    @OneToOne(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private LastCollege lastCollege;

    @OneToOne(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private GuardianInfo guardianInfo;

    @OneToOne(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private PaymentDetails paymentDetails;

    @OneToOne(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Subjects subjects;

    @OneToOne(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private BioFocalSubject bioFocalSubject;

}
