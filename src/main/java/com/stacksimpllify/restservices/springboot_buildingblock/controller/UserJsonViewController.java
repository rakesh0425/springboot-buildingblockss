package com.stacksimpllify.restservices.springboot_buildingblock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.stacksimpllify.restservices.springboot_buildingblock.entities.User;
import com.stacksimpllify.restservices.springboot_buildingblock.entities.Views;
import com.stacksimpllify.restservices.springboot_buildingblock.exceptions.UserNotFoundException;
import com.stacksimpllify.restservices.springboot_buildingblock.services.UserService;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/jsonview/users")
public class UserJsonViewController {
	
	@Autowired
	private UserService service;
	
	@JsonView(Views.External.class)
	@GetMapping(path="/external/{id}")
	private User getUserById(@PathVariable("id") @Min(1)  Long id) {
		try {
		return service.getUserById(id).get();
		}catch(UserNotFoundException e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}

	@JsonView(Views.Internal.class)
	@GetMapping(path="/internal/{id}")
	private User getUserById2(@PathVariable("id") @Min(1)  Long id) {
		try {
		return service.getUserById(id).get();
		}catch(UserNotFoundException e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}
}
