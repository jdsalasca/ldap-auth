package com.jdsk.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jdsk.entities.LdapUser;
import com.jdsk.services.LdapService;
import com.jdsk.utils.DefaultResponse;

import jakarta.validation.Valid;

@RestController
public class UserController {
	@Autowired
	LdapService ldapService;
	
	
	  @GetMapping("/users")
	  public List<?>index() {
	    return ldapService.getAll();
	  }
	  @PostMapping("/users")
	  public ResponseEntity<DefaultResponse<LdapUser>> createUser(@Valid @RequestBody LdapUser ldapUser, BindingResult bindingResult) {
	    return ldapService.create(ldapUser, bindingResult);
	  }
	  
	  @GetMapping("/users/userName/{userName}")
	  public LdapUser index(@PathVariable String userName) {
	    return ldapService.search(userName);
	  }
}
