package com.stacksimpllify.restservices.springboot_buildingblock.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimpllify.restservices.springboot_buildingblock.dto.UserMmDto;
import com.stacksimpllify.restservices.springboot_buildingblock.entities.User;
import com.stacksimpllify.restservices.springboot_buildingblock.exceptions.UserNotFoundException;
import com.stacksimpllify.restservices.springboot_buildingblock.services.UserService;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/modelmapper/users")
public class UserMmController {

	@Autowired
	private UserService service;
	
	@Autowired
	private ModelMapper mapper;
	@GetMapping(path="/{id}")
	private UserMmDto getUserById(@PathVariable("id") @Min(1)  Long id) throws UserNotFoundException {
		
		User user =  service.getUserById(id).get();
		
		UserMmDto userMmDto = mapper.map(user, UserMmDto.class);
		return userMmDto;
	}
}
