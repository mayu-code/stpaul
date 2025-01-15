package com.college.stpaul.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.stpaul.Helper.DateTimeFormat;
import com.college.stpaul.entities.AdmissionForm;
import com.college.stpaul.entities.BankDetails;
import com.college.stpaul.entities.BioFocalSubject;
import com.college.stpaul.entities.Documents;
import com.college.stpaul.entities.GuardianInfo;
import com.college.stpaul.entities.LastCollege;
import com.college.stpaul.entities.Student;
import com.college.stpaul.entities.Subject;
import com.college.stpaul.entities.Subjects;
import com.college.stpaul.entities.User;
import com.college.stpaul.request.NewAdmissionRequest;
import com.college.stpaul.response.AdminssionResponse;
import com.college.stpaul.services.serviceImpl.AdmissionFormImpl;
import com.college.stpaul.services.serviceImpl.BankDetailsServiceImpl;
import com.college.stpaul.services.serviceImpl.BioFocalSubjectServiceImpl;
import com.college.stpaul.services.serviceImpl.DocumentsServiceImpl;
import com.college.stpaul.services.serviceImpl.GuardianInfoServiceImpl;
import com.college.stpaul.services.serviceImpl.LastCollegeServiceImpl;
import com.college.stpaul.services.serviceImpl.StudentServiceImpl;
import com.college.stpaul.services.serviceImpl.SubjectServiceImpl;
import com.college.stpaul.services.serviceImpl.SubjectsServiceImpl;
import com.college.stpaul.services.serviceImpl.UserServiceImpl;

@RestController
@RequestMapping("adminuser")
@CrossOrigin(origins = {"http://localhost:5173/","http://localhost:5174/"})
public class AdmissionController {
    
        @Autowired
        private AdmissionFormImpl admissionFormImpl;
    
        @Autowired
        private UserServiceImpl userServiceImpl;
    
        @Autowired
        private StudentServiceImpl studentServiceImpl;
    
        @Autowired
        private BankDetailsServiceImpl bankDetailsServiceImpl;
    
        @Autowired
        private LastCollegeServiceImpl lastCollegeServiceImpl;
    
        @Autowired
        private GuardianInfoServiceImpl guardianInfoServiceImpl;
    
        @Autowired
        private DocumentsServiceImpl documentsServiceImpl;
    
        @Autowired
        private SubjectsServiceImpl subjectsServiceImpl;
    
        @Autowired
        private BioFocalSubjectServiceImpl bioFocalSubjectServiceImpl;

        @Autowired
        private SubjectServiceImpl subjectServiceImpl;
    
    
        @PostMapping("/newAdmissionForm")
        public ResponseEntity<AdminssionResponse> newAdmission(@RequestHeader("Authorization") String jwt,@RequestBody NewAdmissionRequest admissionRequest){
            AdminssionResponse response = new AdminssionResponse();
            User user = this.userServiceImpl.getUserByJWT(jwt);
            AdmissionForm admissionForm= admissionRequest.getAdmissionForm();
            Student student = admissionRequest.getStudent() ;
            BankDetails bankDetails = admissionRequest.getBankDetails();
            LastCollege lastCollege = admissionRequest.getLastCollege();
            GuardianInfo guardianInfo = admissionRequest.getGuardianInfo();
            Subjects subjects = admissionRequest.getSubjects();
            BioFocalSubject bioFocalSubject = admissionRequest.getBioFocalSubject();
            Documents sports = admissionRequest.getSports();
            List<Documents> documents = admissionRequest.getDocuments(); 
    
            try{
                admissionForm.setUser(user);
                admissionForm.setAdmissionDate(DateTimeFormat.format(LocalDateTime.now()));
                admissionForm = this.admissionFormImpl.addAdmissionForm(admissionForm);

                student.setSession(admissionForm.getSession());
                student.setCurrentClass(admissionForm.getStdClass());
                student.setAdmissionForm(admissionForm);
                student = this.studentServiceImpl.addStudent(student);
    
                bankDetails.setStudent(student);
                this.bankDetailsServiceImpl.addBankDetails(bankDetails);
             
                lastCollege.setStudent(student);
                this.lastCollegeServiceImpl.addLastCollege(lastCollege);
              
                guardianInfo.setStudent(student);
                this.guardianInfoServiceImpl.addGuardianInfo(guardianInfo); 
                
        
                if(sports!=null){
                    sports.setStudent(student);
                    this.documentsServiceImpl.addDocuments(sports);
                }

                if(documents!=null){
                    for(Documents d:documents){
                        d.setStudent(student);
                        this.documentsServiceImpl.addDocuments(d);
                    }
                }
                
                if(subjects!=null){
                    List<Subject> sSubject = subjects.getSubject();
                    subjects.setSubject(null);
                    subjects.setStudent(student);
                    subjects = this.subjectsServiceImpl.addSubjects(subjects);
                    for(Subject s:sSubject){
                        s.setSubjects(subjects);
                        this.subjectServiceImpl.addSubject(s);
                    }
                }
               
            if(bioFocalSubject!=null){
                List<Subject> bSubject = bioFocalSubject.getSubject();
                bioFocalSubject.setSubject(null);
                bioFocalSubject.setStudent(student);
                this.bioFocalSubjectServiceImpl.addBioFocalSubject(bioFocalSubject);
                for(Subject s:bSubject){
                    s.setBioFocalSubject(bioFocalSubject);
                    this.subjectServiceImpl.addSubject(s);
                }
            }
            response.setId(student.getId());
            response.setHttpStatus(HttpStatus.OK);
            response.setHttpStatusCode(200);
            response.setMessage("Admission Form submit successfully !");
            return ResponseEntity.of(Optional.of(response));

        }catch(Exception e){
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setHttpStatusCode(500);;
            response.setMessage("something went wrong !");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }
}
