package com.devcenter.apiRest.exception;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.devcenter.apiRest.model.Response;

@RestControllerAdvice
public class MyControllerAdvice {
 
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Response> handleControllerException(HttpServletRequest request,
                                                       NoHandlerFoundException ex) {
    	Response response = new Response(new Timestamp(System.currentTimeMillis()).toString(), ex.getMessage(),
				HttpStatus.NOT_FOUND.toString(), ex.getMessage());
    	
    	return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }
}
