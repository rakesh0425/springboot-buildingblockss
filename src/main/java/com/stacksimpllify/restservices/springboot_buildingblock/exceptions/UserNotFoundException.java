package com.stacksimpllify.restservices.springboot_buildingblock.exceptions;

public class UserNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException() {
		super();
	}
	
	

}
