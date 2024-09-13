package com.springboot.rest.petpeers.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerAdvice  {
    @ResponseStatus(HttpStatus.BAD_REQUEST)//custome exception
    @ExceptionHandler(ApplicationException.class)
    public ErrorResponse  handleApplicationException(ApplicationException ex, WebRequest request){
        List<String> errors= new ArrayList<>();
        errors.add(ex.getMessage());

        //In case of nested exception
        if(ex.getCause() != null){
            errors.add(ex.getCause().getMessage());
        }
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(),errors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String,Object>> handleDataIntegrityViolationException(DataIntegrityViolationException ex){
        Map<String,Object> errorDetails= new HashMap<>();

        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("Status",HttpStatus.CONFLICT.value());
        errorDetails.put("error","Data Integrity Violation");
        errorDetails.put("message", ExceptionUtils.getRootCauseMessage(ex));
        errorDetails.put("path","/petShop/saveUser");

        return new ResponseEntity<>(errorDetails,HttpStatus.CONFLICT);
    }

}
