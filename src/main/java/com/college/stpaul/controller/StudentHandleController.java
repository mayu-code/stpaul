package com.college.stpaul.controller;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.college.stpaul.constants.Result;
import com.college.stpaul.response.DataResponse;
import com.college.stpaul.services.serviceImpl.StudentServiceImpl;

@RestController
@RequestMapping("/adminuser")
@CrossOrigin(origins = {"http://localhost:5173/","http://localhost:5174/"})
public class StudentHandleController {
    
    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @GetMapping("/getStudent")
    public ResponseEntity<DataResponse> getStudents(@RequestHeader("Authorization")String jwt,
                                @RequestParam(required = false) String query,
                                @RequestParam(required = false) Result result,
                                @RequestParam(required = false) String currentClass,
                                @RequestParam(required = false) String session
                                ){
        
        DataResponse response = new DataResponse();
        System.out.println(query);
        try{

            response.setData(this.studentServiceImpl.getStudentByField(query,result,currentClass,session));
            // if(result.isEmpty()){
            //     response.setData(this.studentServiceImpl.getStudentByField(null,null,null,null));
            // }
            // else{
            //     response.setData(this.studentServiceImpl.getStudentByField(query,Result.valueOf(result),currentClass,session));
            // }
            response.setHttpStatus(HttpStatus.OK);
            response.setHttpStatusCode(200);
            response.setMessage("get Students Successfully !");
            return ResponseEntity.of(Optional.of(response));

        }catch(Exception e){
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setHttpStatusCode(500);
            response.setMessage("something went wrong !");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
                                
    }
    
}
