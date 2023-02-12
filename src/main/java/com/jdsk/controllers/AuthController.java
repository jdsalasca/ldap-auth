package com.jdsk.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

	  @GetMapping("/")
	  public String index() {
	    return "Welcome to the home page!";
	  }
}
