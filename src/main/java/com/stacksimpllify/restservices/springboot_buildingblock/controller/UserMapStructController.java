package com.stacksimpllify.restservices.springboot_buildingblock.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimpllify.restservices.springboot_buildingblock.dto.UserMsDto;
import com.stacksimpllify.restservices.springboot_buildingblock.entities.User;
import com.stacksimpllify.restservices.springboot_buildingblock.exceptions.UserNotFoundException;
import com.stacksimpllify.restservices.springboot_buildingblock.mappers.UserMapper;
import com.stacksimpllify.restservices.springboot_buildingblock.repositories.UserRepository;
import com.stacksimpllify.restservices.springboot_buildingblock.services.UserService;


@RestController
@RequestMapping("/mapstruct/users")
public class UserMapStructController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserMapper userMapper;
	
	@GetMapping
	private List<UserMsDto> getAllUsers(){
		return userMapper.usersToUserDtos(repository.findAll());
	}
	
	
	@GetMapping(path="/{id}")
	private UserMsDto getUserById(@PathVariable("id") Long id) throws UserNotFoundException {
		Optional<User> optionaUser = service.getUserById(id); 
		
		return userMapper.userToUserMsDto(optionaUser.get());
	}

}
