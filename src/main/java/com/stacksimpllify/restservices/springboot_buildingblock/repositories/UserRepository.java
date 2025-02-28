package com.stacksimpllify.restservices.springboot_buildingblock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stacksimpllify.restservices.springboot_buildingblock.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByUsername(String username);

}
