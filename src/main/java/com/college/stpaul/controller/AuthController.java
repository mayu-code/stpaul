package com.college.stpaul.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.stpaul.Helper.DateTimeFormat;
import com.college.stpaul.JwtSecurity.CustomUserDetail;
import com.college.stpaul.JwtSecurity.JwtProvider;
import com.college.stpaul.entities.User;
import com.college.stpaul.request.LoginRequest;
import com.college.stpaul.response.LoginResponse;
import com.college.stpaul.response.SuccessResponse;
import com.college.stpaul.services.serviceImpl.UserServiceImpl;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:5173/","http://localhost:5174/"})
public class AuthController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    CustomUserDetail userDetail;

    
    @PostMapping("/register")
    public ResponseEntity<SuccessResponse> registrationManager(@RequestBody User user){
        User u = this.userServiceImpl.getUserByEmail(user.getEmail());
        SuccessResponse response = new SuccessResponse();
        if(u!=null){
            response.setHttpStatus(HttpStatus.ALREADY_REPORTED);
            response.setHttpStatusCode(409);
            response.setMessage("Email already exits !");
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(response);
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRegistrationDate(DateTimeFormat.format(LocalDateTime.now()));
        try{
            this.userServiceImpl.addUser(user);
            response.setHttpStatus(HttpStatus.OK);
            response.setHttpStatusCode(200);
            response.setMessage("Register User Successfully ! ");
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setHttpStatusCode(500);;
            response.setMessage("something went wrong !");
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(response);
        }
        
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest request){
        LoginResponse response = new LoginResponse();
        User user = this.userServiceImpl.getUserByEmail(request.getEmail());

        if(user==null){
            response.setHttpStatus(HttpStatus.UNAUTHORIZED);
            response.setHttpStatusCode(500);;
            response.setMessage("Invalid email or password");
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(response);
        }

        UserDetails userDetails = userDetail.loadUserByUsername(request.getEmail());
        boolean isPasswordValid = new BCryptPasswordEncoder().matches(request.getPassword(),userDetails.getPassword() );

        if(!isPasswordValid){
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            response.setHttpStatusCode(500);;
            response.setMessage("Invalid email or password");
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(response);
        }

        Authentication authentication = authenticate(user.getEmail(), request.getPassword());
        String token = JwtProvider.generateJwtToken(authentication);
        user.setLoginDate(DateTimeFormat.format(LocalDateTime.now()));
        try{
            this.userServiceImpl.addUser(user);
            response.setHttpStatus(HttpStatus.OK);
            response.setHttpStatusCode(200);
            response.setToken(token);
            response.setMessage("login successful ! ");
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setHttpStatusCode(500);;
            response.setMessage("something went wrong !");
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(response);
        }
    }


    private Authentication authenticate(String email , String password){
        UserDetails details = userDetail.loadUserByUsername(email);
        if(details==null){
            throw new UsernameNotFoundException("Invalid credentials ");
        }

        return new UsernamePasswordAuthenticationToken(details,password,details.getAuthorities());

    }
}
