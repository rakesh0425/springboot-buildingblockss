package com.stacksimpllify.restservices.springboot_buildingblock.dto;

import java.util.List;

import org.springframework.web.bind.annotation.Mapping;

import com.stacksimpllify.restservices.springboot_buildingblock.entities.Order;

public class UserMmDto {
	
	private Long userId;
	private String username;
	private String lastname;
	private List<Order> orders;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
