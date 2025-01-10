package com.college.stpaul.entities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Subjects {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String stream;
    private String subStream;
    

    @OneToMany(mappedBy = "subjects",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Subject> subject;

    @OneToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;
}
