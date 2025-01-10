package com.college.stpaul.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String medium="English";


    @ManyToOne
    @JoinColumn(name = "subjects_id")
    @JsonIgnore
    private Subjects subjects;

    @ManyToOne
    @JoinColumn(name = "bioFocalSubject_id")
    @JsonIgnore
    private BioFocalSubject bioFocalSubject;
}
