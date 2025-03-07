package com.stacksimpllify.restservices.springboot_buildingblock.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimpllify.restservices.springboot_buildingblock.dtos.UserDto1;
import com.stacksimpllify.restservices.springboot_buildingblock.dtos.UserDtoV2;
import com.stacksimpllify.restservices.springboot_buildingblock.entities.User;
import com.stacksimpllify.restservices.springboot_buildingblock.exceptions.UserNotFoundException;
import com.stacksimpllify.restservices.springboot_buildingblock.services.UserService;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/versioning/uri/users")
public class UserUriVersioningController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@GetMapping({ "/v1.0/{id}","/v1.1/{id}" })
	private UserDto1 getUserById(@PathVariable("id") @Min(1)  Long id) {
		try {
		return modelMapper.map(service.getUserById(id).get(), UserDto1.class);
		}catch(UserNotFoundException e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}
	
	@GetMapping({ "/v2.0/{id}" })
	private UserDtoV2 getUserById2(@PathVariable("id") @Min(1)  Long id) {
		try {
		return modelMapper.map(service.getUserById(id).get(), UserDtoV2.class);
		}catch(UserNotFoundException e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}

}
