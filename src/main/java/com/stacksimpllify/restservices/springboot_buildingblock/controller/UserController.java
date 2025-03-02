package com.stacksimpllify.restservices.springboot_buildingblock.controller;

import org.springframework.http.HttpHeaders;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimpllify.restservices.springboot_buildingblock.entities.User;
import com.stacksimpllify.restservices.springboot_buildingblock.exceptions.UserExistsException;
import com.stacksimpllify.restservices.springboot_buildingblock.exceptions.UserNameNotFoundException;
import com.stacksimpllify.restservices.springboot_buildingblock.exceptions.UserNotFoundException;
import com.stacksimpllify.restservices.springboot_buildingblock.services.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.executable.ValidateOnExecution;

//import jakarta.validation.Valid;
//import jakarta.validation.constraints.Min;
//import jakarta.validation.executable.ValidateOnExecution;

@RestController
@ValidateOnExecution
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping(path="/users")
	private List<User> getAllUsers(){
		return service.getAllUsers();
	}
	
	
	@GetMapping(path="/users/{id}")
	private User getUserById(@PathVariable("id") @Min(1)  Long id) {
		try {
		return service.getUserById(id).get();
		}catch(UserNotFoundException e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}
	
	@PostMapping(path="users")
	private ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder) {

		try {
			 service.createUserDetails(user);
			 HttpHeaders headers= new HttpHeaders();
			 headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			 return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
		} catch (UserExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	
	@PutMapping(path="/users/{id}")
	private User updateUserById(@PathVariable("id") Long id,@RequestBody User user) {
		
		try {
			return service.updateUserById(id, user);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}
	
	@DeleteMapping(path="/users/{id}")
	private void  deleteUserById(@PathVariable("id") Long id) {
		try {
			service.deleteUserById(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	@GetMapping(path="/users/byUsername/{username}")
	private User findByUsername(@PathVariable("username") String username) throws UserNameNotFoundException {
		User user = service.findByUserName(username);
		if(user == null)
			throw new UserNameNotFoundException("Username - "+username+ " not found in repository");
		return user;
	}
}
