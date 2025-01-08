package com.college.stpaul.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class AdmissionForm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long formNO;
    private String stdClass;
    private String section;
    private String session;
    private String admissionDate;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

}
