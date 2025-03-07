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
import com.stacksimpllify.restservices.springboot_buildingblock.exceptions.UserNotFoundException;
import com.stacksimpllify.restservices.springboot_buildingblock.services.UserService;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/versioning/header/users")
public class UserHeaderVersioningController2 {
	@Autowired
	private UserService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@GetMapping(value = "{id}",headers = "API-VERSION=1")
	private UserDto1 getUserById(@PathVariable("id") @Min(1)  Long id) {
		try {
		return modelMapper.map(service.getUserById(id).get(), UserDto1.class);
		}catch(UserNotFoundException e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}
	
	@GetMapping(value = "{id}",headers = "API-VERSION=2")
	private UserDtoV2 getUserById2(@PathVariable("id") @Min(1)  Long id) {
		try {
		return modelMapper.map(service.getUserById(id).get(), UserDtoV2.class);
		}catch(UserNotFoundException e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}
}
