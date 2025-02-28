package com.stacksimpllify.restservices.springboot_buildingblock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksimpllify.restservices.springboot_buildingblock.entities.User;
import com.stacksimpllify.restservices.springboot_buildingblock.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> getAllUsers(){
		return repository.findAll();
		
	}
	
	public User createUserDetails(User user) {
		return repository.save(user);
	}
	
	public User getUserById(Long id) {
		return repository.getReferenceById(id);
	}
	
    public User updateUserById(Long id,User user) {
    	user.setId(id);
    	return repository.save(user);
    }
    
    public void deleteUserById(Long id) {
    	repository.deleteById(id);
    }
    
    public User findByUserName(String username) {
    	return repository.findByUsername(username);
    }
}
