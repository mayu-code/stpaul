package com.college.stpaul.response;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class AdminssionResponse {
    private HttpStatus httpStatus;
    private String message;
    private int httpStatusCode;
    private long id;
}
