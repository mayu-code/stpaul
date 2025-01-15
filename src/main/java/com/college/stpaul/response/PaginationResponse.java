package com.college.stpaul.response;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class PaginationResponse {
    private HttpStatus httpStatus;
    private String message;
    private int httpStatusCode;
    private int pages;
    private long students;
}
