package com.college.stpaul.request;

import com.college.stpaul.entities.AdmissionForm;
import com.college.stpaul.entities.BankDetails;
import com.college.stpaul.entities.GuardianInfo;
import com.college.stpaul.entities.LastCollege;
import com.college.stpaul.entities.Student;

import lombok.Data;

@Data
public class NewAdmissionRequest {
    private AdmissionForm admissionForm;
    private Student student;
    private BankDetails bankDetails;
    private LastCollege lastCollege;
    private GuardianInfo guardianInfo;
}