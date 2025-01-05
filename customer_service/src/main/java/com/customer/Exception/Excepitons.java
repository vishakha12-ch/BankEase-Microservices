package com.customer.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class Excepitons {
	
	@ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomResponse> handleCustomException(CustomException ex) {
		ex.printStackTrace();
		CustomResponse response = new CustomResponse(
                ex.getMessage(),
                ex.getTimestamp()
        );
			
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
	
	// Handle other exceptions if needed
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponse> handleException(Exception ex) {
        CustomResponse response = new CustomResponse(
                "Something went wrong",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
