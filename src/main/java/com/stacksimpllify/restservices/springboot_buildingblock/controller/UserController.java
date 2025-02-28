package com.stacksimpllify.restservices.springboot_buildingblock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimpllify.restservices.springboot_buildingblock.entities.User;
import com.stacksimpllify.restservices.springboot_buildingblock.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping(path="/users")
	private List<User> getAllUsers(){
		return service.getAllUsers();
	}
	
	
	@GetMapping(path="/users/{id}")
	private User getUserById(@PathVariable("id") Long id) {
		return service.getUserById(id);
	}
	
	@PostMapping(path="users")
	private User createUser(@RequestBody User user) {
		return service.createUserDetails(user);
	}

	
	@PutMapping(path="/users/{id}")
	private User updateUserById(@PathVariable("id") Long id,@RequestBody User user) {
		return service.updateUserById(id, user);
	}
	
	@DeleteMapping(path="/users/{id}")
	private void  deleteUserById(@PathVariable("id") Long id) {
		service.deleteUserById(id);
	}
	
	@GetMapping(path="/users/byUsername/{username}")
	private User findByUsername(@PathVariable("username") String username) {
		return service.findByUserName(username);
	}
}
