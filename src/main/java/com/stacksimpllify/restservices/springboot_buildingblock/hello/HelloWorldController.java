package com.stacksimpllify.restservices.springboot_buildingblock.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@GetMapping("/helloWorld1")
	//@RequestMapping(method=RequestMethod.GET,path="/helloWorld")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping("/helloWorldBean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Rakesh","Kumar","Hyd");
	}
}
