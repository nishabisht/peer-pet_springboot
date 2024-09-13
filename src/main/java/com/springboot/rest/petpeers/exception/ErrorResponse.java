package com.springboot.rest.petpeers.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private List<String> errorMessage;


    public ErrorResponse(int value, String badRequest,List<String> errorMessage){
        timestamp=LocalDateTime.now();
        this.status=value;
        this.error=badRequest;
        this.errorMessage=errorMessage;
    }
}
