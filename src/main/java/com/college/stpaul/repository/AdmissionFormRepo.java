package com.college.stpaul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.stpaul.entities.AdmissionForm;
import java.util.List;


public interface AdmissionFormRepo extends JpaRepository<AdmissionForm,Long> {
    AdmissionForm  findByFormNO(long formNO);
}
