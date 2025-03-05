package com.stacksimpllify.restservices.springboot_buildingblock.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.stacksimpllify.restservices.springboot_buildingblock.entities.User;
import com.stacksimpllify.restservices.springboot_buildingblock.exceptions.UserNotFoundException;
import com.stacksimpllify.restservices.springboot_buildingblock.services.UserService;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping(value = "/jacksonfilter/users")
public class UserMappingJacksonController {
	
	@Autowired
	private UserService service;
	
	@GetMapping(path="/{id}")
	private MappingJacksonValue getUserById(@PathVariable("id") @Min(1)  Long id) {
		try {
		User user =  service.getUserById(id).get();
		Set<String> fields = new HashSet<String>();
		fields.add("id");
		fields.add("username");
		fields.add("ssn");
		fields.add("orders");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter", 
				SimpleBeanPropertyFilter.filterOutAllExcept(fields ));
		MappingJacksonValue mapper = new MappingJacksonValue(user);
		mapper.setFilters(filterProvider);
		return mapper;
		}catch(UserNotFoundException e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}
	
	@GetMapping(path="/params/{id}")
	private MappingJacksonValue getUserById2(@PathVariable("id") @Min(1)  Long id,@RequestParam Set<String> fields) {
		try {
		User user =  service.getUserById(id).get();
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter", 
				SimpleBeanPropertyFilter.filterOutAllExcept(fields ));
		MappingJacksonValue mapper = new MappingJacksonValue(user);
		mapper.setFilters(filterProvider);
		return mapper;
		}catch(UserNotFoundException e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}

}
