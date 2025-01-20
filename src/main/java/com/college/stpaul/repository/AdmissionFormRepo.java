package com.college.stpaul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.stpaul.entities.AdmissionForm;


public interface AdmissionFormRepo extends JpaRepository<AdmissionForm,Long> {
    AdmissionForm  findByFormNo(long formNo);
}
