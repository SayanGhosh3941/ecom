package com.codesnippet.ecom.Controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codesnippet.ecom.Entity.ErrorResponse;
import com.codesnippet.ecom.exceptions.ProductNotFoundException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	//Generic Exception handling
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGenericException(Exception e){
		log.error("Enexpected error occured "+e);
		e.printStackTrace();
		return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//Business specific exception handling
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException e) {
		ErrorResponse productNotFound = new ErrorResponse(LocalDateTime.now(), e.getMessage(), "Product not found");
		return new ResponseEntity<>(productNotFound, HttpStatus.NOT_FOUND);
	}

}
