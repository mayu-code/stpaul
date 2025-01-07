package com.college.stpaul.response;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class SuccessResponse {
    private HttpStatus httpStatus;
    private String message;
    private int httpStatusCode;
}
