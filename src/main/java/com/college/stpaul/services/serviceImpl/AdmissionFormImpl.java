package com.college.stpaul.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.stpaul.entities.AdmissionForm;
import com.college.stpaul.repository.AdmissionFormRepo;
import com.college.stpaul.services.serviceInterface.AdmissionFormService;

@Service
public class AdmissionFormImpl implements AdmissionFormService {

    @Autowired
    private AdmissionFormRepo admissionFormRepo;

    @Override
    public AdmissionForm addAdmissionForm(AdmissionForm admissionForm) {
        return this.admissionFormRepo.save(admissionForm);
    }

    @Override
    public AdmissionForm getAdmissionFormByFromNo(long formNo) {
        return this.admissionFormRepo.findByFormNo(formNo);
    }
   
}
