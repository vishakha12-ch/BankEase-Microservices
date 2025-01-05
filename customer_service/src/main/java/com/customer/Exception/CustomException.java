package com.customer.Exception;

import java.time.LocalDateTime;

public class CustomException extends RuntimeException{
	
    private LocalDateTime timestamp;

    public CustomException( String message, LocalDateTime timestamp) {
        super(message);
        this.timestamp = timestamp;
    }

    

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

}
