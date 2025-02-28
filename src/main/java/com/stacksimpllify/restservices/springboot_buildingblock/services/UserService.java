package com.stacksimpllify.restservices.springboot_buildingblock.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.stacksimpllify.restservices.springboot_buildingblock.entities.User;
import com.stacksimpllify.restservices.springboot_buildingblock.exceptions.UserExistsException;
import com.stacksimpllify.restservices.springboot_buildingblock.exceptions.UserNotFoundException;
import com.stacksimpllify.restservices.springboot_buildingblock.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> getAllUsers(){
		return repository.findAll();
		
	}
	
	public User createUserDetails(User user) throws UserExistsException {
		User optionalUser = repository.findByUsername(user.getUsername());
		if(optionalUser != null) {
			throw new UserExistsException("Error occured while creating user");// TODO: handle exception
		}
		try {
			return repository.save(user);
		}catch (Exception e) {
			throw new UserExistsException("Error occured while creating user");// TODO: handle exception
		}
	}
	
	public Optional<User> getUserById(Long id) throws UserNotFoundException{
		Optional<User> user = repository.findById(id);
		if(user.isPresent()) {
			return user;
		}else {
			throw new UserNotFoundException("User Not found in user repository");
		}
	}
	
    public User updateUserById(Long id,User user) throws UserNotFoundException {
    	try {
    		getUserById(id);
    	}catch(Exception e) {
    		throw new UserNotFoundException("User Not found to update in user repository");
    	}
    	user.setId(id);
    	return repository.save(user);
    }
    
    public void deleteUserById(Long id) throws UserNotFoundException {
    	try {
    		getUserById(id);
    	}catch(Exception e) {
    		throw new UserNotFoundException("User Not found to delete in user repository");
    	}
    	repository.deleteById(id);
    }
    
    public User findByUserName(String username) {
    	return repository.findByUsername(username);
    }
}
