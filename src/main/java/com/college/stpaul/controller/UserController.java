package com.college.stpaul.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.stpaul.entities.User;
import com.college.stpaul.response.DataResponse;
import com.college.stpaul.services.serviceImpl.UserServiceImpl;

@RestController
@RequestMapping("/adminuser")
@CrossOrigin(origins = {"http://localhost:5173/","http://localhost:5174/"})
public class UserController {
    
    @Autowired
    private UserServiceImpl userServiceImpl; 

    @GetMapping("/getProfile")
    public ResponseEntity<DataResponse> getProfile(@RequestHeader("Authorization") String jwt){
        DataResponse response = new DataResponse();
        User user = this.userServiceImpl.getUserByJWT(jwt);
        try{
            response.setData(user);
            response.setHttpStatus(HttpStatus.OK);
            response.setHttpStatusCode(200);
            response.setMessage("get profile successfully !");
            return ResponseEntity.of(Optional.of(response));

        }catch(Exception e){

            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setHttpStatusCode(500);;
            response.setMessage("something went wrong !");
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(response);
        }
    }
}
