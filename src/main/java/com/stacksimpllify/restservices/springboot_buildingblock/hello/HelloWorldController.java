package com.stacksimpllify.restservices.springboot_buildingblock.hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceResourceBundle;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	ResourceBundleMessageSource messageSourceResourceBundle;
	
	@GetMapping("/helloWorld1")
	//@RequestMapping(method=RequestMethod.GET,path="/helloWorld")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping("/helloWorldBean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Rakesh","Kumar","Hyd");
	}
	
	@GetMapping("/int-msg")
	public String sayHello(@RequestHeader(name="Accept-Language", required=false) String locale) {
		return messageSourceResourceBundle.getMessage("label.hello",null, new Locale(locale));
	}
}
