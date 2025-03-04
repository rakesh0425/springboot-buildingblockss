package com.stacksimpllify.restservices.springboot_buildingblock.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimpllify.restservices.springboot_buildingblock.entities.Order;
import com.stacksimpllify.restservices.springboot_buildingblock.entities.User;
import com.stacksimpllify.restservices.springboot_buildingblock.exceptions.UserNotFoundException;
import com.stacksimpllify.restservices.springboot_buildingblock.repositories.OrderRespository;
import com.stacksimpllify.restservices.springboot_buildingblock.repositories.UserRepository;
import com.stacksimpllify.restservices.springboot_buildingblock.services.UserService;

@RestController
@RequestMapping(value = "/heateoas/users")
public class OrderhateoasController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRespository orderRespository;
	
	
	@GetMapping("/{userId}/orders")
	public List<Order> getAllOrders(@PathVariable Long userId) throws UserNotFoundException{
		
		Optional<User> userOptional = userRepository.findById(userId);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User Not Found in Repository");
		}
		
		List<Order> orders = userOptional.get().getOrders();
		for(Order order:orders) {
			Long orderId = order.getOrderid();
			Link selfLink2 = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId).slash("orders").slash(orderId).withSelfRel();
			order.add(selfLink2);
		}
		
		return orders;
		
	} 

}
