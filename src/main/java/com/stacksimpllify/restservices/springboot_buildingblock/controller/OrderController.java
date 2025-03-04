package com.stacksimpllify.restservices.springboot_buildingblock.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimpllify.restservices.springboot_buildingblock.entities.Order;
import com.stacksimpllify.restservices.springboot_buildingblock.entities.User;
import com.stacksimpllify.restservices.springboot_buildingblock.exceptions.UserNotFoundException;
import com.stacksimpllify.restservices.springboot_buildingblock.repositories.OrderRespository;
import com.stacksimpllify.restservices.springboot_buildingblock.repositories.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class OrderController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRespository orderRespository;
	
	@GetMapping("/{userId}/orders")
	private List<Order> getAllOrders(@PathVariable Long userId) throws UserNotFoundException{
		
		Optional<User> userOptional = userRepository.findById(userId);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User Not Found in Repository");
		}
		
		return userOptional.get().getOrders();
		
	}
	
	@PostMapping("/{userId}/orders")
	private Order createOrder(@PathVariable Long userId,@RequestBody Order order) throws UserNotFoundException{ 
		Optional<User> userOptional = userRepository.findById(userId);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User Not Found in Repository");
		}
		
		User user = userOptional.get();
		order.setUser(user);
		return orderRespository.save(order);		
	}
	
	@GetMapping("{userId}/orders/{orderId}")
	private Order getOrderByOrderId(@PathVariable Long userId, @PathVariable Long orderId) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userId);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User Not Found in Repository");
		}	
		
		Order resOrder = null;
		List<Order> orders =  userOptional.get().getOrders();
		for(Order order : orders) {
			if(order.getOrderid().equals(orderId))
				return order;
		}
		//System.out.println("Result ---"+resOrder.toString());
		return null;
	}

}
