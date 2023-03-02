package com.example.stardew_valley_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello_world")
public class HelloWorldController {
	
	@GetMapping()
	public String helloWorld() {
		return "hello world";
	}

}
