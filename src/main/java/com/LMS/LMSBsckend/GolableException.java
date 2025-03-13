package com.LMS.LMSBsckend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.LMS.LMSBsckend.Exception.HostelException;

import jakarta.servlet.ServletException;

@ControllerAdvice
public class GolableException {
	
	@ExceptionHandler(HostelException.class)
	public ResponseEntity<String> banknotfound(HostelException bankexc){
		return new ResponseEntity<>(bankexc.getMessage(),HttpStatus.NOT_FOUND);	
		 
	}
	
	@ExceptionHandler(ServletException.class)
	public ResponseEntity<String> tockin(ServletException bankexc){
		return new ResponseEntity<>(bankexc.getMessage(),HttpStatus.NOT_FOUND);	
		 
	}

}
