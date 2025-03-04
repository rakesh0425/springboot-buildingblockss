package com.stacksimpllify.restservices.springboot_buildingblock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimpllify.restservices.springboot_buildingblock.entities.Order;
import com.stacksimpllify.restservices.springboot_buildingblock.entities.User;
import com.stacksimpllify.restservices.springboot_buildingblock.exceptions.UserNotFoundException;
import com.stacksimpllify.restservices.springboot_buildingblock.repositories.UserRepository;
import com.stacksimpllify.restservices.springboot_buildingblock.services.UserService;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping(value = "/heateoas/users")
public class UserHateoasController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;

	@GetMapping
	private RepresentationModel<?> getAllUsers() throws UserNotFoundException{
		List<User> users =  userService.getAllUsers();
		for(User user : users) {
			Long userId = user.getId();
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
			user.add(selfLink);
			
			List<Order> orders = WebMvcLinkBuilder.methodOn(OrderhateoasController.class).getAllOrders(userId);
			Link orderLink = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
			user.add(orderLink);
	
		}
		Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
		//user.add(selfLink);
		RepresentationModel<?> usersRepModel  = RepresentationModel.of(users); 
		usersRepModel.add(selfLink);
		return usersRepModel;
		
	}
	
	
	@GetMapping(path="/{id}")
	private User getUserById(@PathVariable("id") @Min(1)  Long id) {
		try {
		User user =  userService.getUserById(id).get();
		Long userId = user.getId();
		Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
		user.add(selfLink);
		
		//EntityModel<User> entityModel = new 
		
		return user;
		}catch(UserNotFoundException e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}
}
