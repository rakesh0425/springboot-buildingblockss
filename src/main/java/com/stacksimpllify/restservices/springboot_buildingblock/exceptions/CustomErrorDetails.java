package com.stacksimpllify.restservices.springboot_buildingblock.exceptions;

import java.time.LocalDateTime;

public class CustomErrorDetails {
	
	private LocalDateTime timeStamp;
	private String message;
	private String errorDetails;
	public CustomErrorDetails(LocalDateTime timeStamp, String message, String errorDetails) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.errorDetails = errorDetails;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorDetails() {
		return errorDetails;
	}
	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}
	
	
	

}
