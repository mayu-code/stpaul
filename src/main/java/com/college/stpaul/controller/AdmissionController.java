package com.college.stpaul.controller;

import java.net.SecureCacheResponse;
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

import com.college.stpaul.entities.AdmissionForm;
import com.college.stpaul.entities.User;
import com.college.stpaul.response.SuccessResponse;
import com.college.stpaul.services.serviceImpl.AdmissionFormImpl;
import com.college.stpaul.services.serviceImpl.UserServiceImpl;

@RestController
@RequestMapping({"/admin","/manager"})
@CrossOrigin(origins = {"http://localhost:5173/","http://localhost:5174/"})
public class AdmissionController {
    
    @Autowired
    private AdmissionFormImpl admissionFormImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;


    @PostMapping("/newAdmissionForm")
    public ResponseEntity<SuccessResponse> newAdmission(@RequestHeader("Authorization") String jwt,
                                                        @RequestBody AdmissionForm admissionForm){
        SuccessResponse response = new SuccessResponse();
        User user = this.userServiceImpl.getUserByJWT(jwt);
        
        try{
            admissionForm.setUser(user);
            this.admissionFormImpl.addAdmissionForm(admissionForm);
            response.setHttpStatus(HttpStatus.OK);
            response.setHttpStatusCode(200);
            response.setMessage("Admission Form submit successfully !");
            return ResponseEntity.of(Optional.of(response));

        }catch(Exception e){
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setHttpStatusCode(500);;
            response.setMessage("something went wrong !");
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(response);
        }

    }
}
