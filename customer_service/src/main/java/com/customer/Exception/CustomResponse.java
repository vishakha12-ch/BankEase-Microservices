package com.customer.Exception;

import java.time.LocalDateTime;

public class CustomResponse {

	private String message;
    
    private LocalDateTime timestamp;
	/**
	 * @param message
	 
	 * @param timestamp
	 */
	public CustomResponse(String message, LocalDateTime timestamp) {
		super();
		this.message = message;
		
		this.timestamp = timestamp;
	}
    
	public String getMessage() {
        return message;
    }

    

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

}
