package com.group5devs.project2.exceptions;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//remove devtools in pom.xml/not necessarily
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		return super.handleMethodArgumentNotValid(ex, headers, status, request);
	}
	
	
	@ExceptionHandler({NoRequestException.class, NoPendingRequestException.class, NoResolvedRequestException.class})
	protected ResponseEntity<Object> handleNoRequestException(Exception ex){
		Map<String, String> errors= new HashMap<>();
		System.out.println(errors);
		errors.put("date",LocalDate.now()+"");
		errors.put("errorMessage", ex.getMessage());
		System.out.println(errors);
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}
}
