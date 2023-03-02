package com.example.stardew_valley_service.exception;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice()
public class ControllerExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> resourceNotFoundException(ResourceNotFoundException exp) {
		Map<String, Object> errorBody = Map.of("message", exp.getMessage());
		return new ResponseEntity<Map<String,Object>>(errorBody, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String, Object>> constraintViolationException(ConstraintViolationException exp) {
		Map<String, Object> errorBody = new LinkedHashMap<>();
		errorBody.put("message", "data constraint violation");
		
		Map<String, String> violations = exp.getConstraintViolations().stream()
				.collect(Collectors.toMap(violation -> violation.getPropertyPath().toString(), violation -> violation.getMessage()));
		
		errorBody.put("violations", violations);
		
		return new ResponseEntity<Map<String,Object>>(errorBody, HttpStatus.BAD_REQUEST);
	}

}
