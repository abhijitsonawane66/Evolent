package com.evalent.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.evalent.constant.ErrorConstant;
import com.evalent.exception.EmptyInputException;

@ControllerAdvice
public class MyControllerAdvice {

	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<String> handleEmptyInputException(EmptyInputException emptyInputException){
		
		return new ResponseEntity<String>(ErrorConstant.EMPTY_INPUT,HttpStatus.BAD_REQUEST);
	}
	
	
}
