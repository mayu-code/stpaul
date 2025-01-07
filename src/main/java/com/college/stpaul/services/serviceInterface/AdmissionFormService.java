package com.college.stpaul.services.serviceInterface;

import com.college.stpaul.entities.AdmissionForm;

public interface AdmissionFormService {

    AdmissionForm addAdmissionForm(AdmissionForm admissionForm);
    AdmissionForm getAdmissionFormByFromNo(long formNo);
    
} 