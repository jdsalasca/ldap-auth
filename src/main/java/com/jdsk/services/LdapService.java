package com.jdsk.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.jdsk.entities.LdapUser;
import com.jdsk.utils.DefaultResponse;

public interface LdapService {
	
	public LdapUser search(String username);
	public List	<LdapUser> getAll();
	ResponseEntity<DefaultResponse<LdapUser>> create(LdapUser ldapUser, BindingResult bindingResult);
	ResponseEntity<DefaultResponse<Boolean>> authenticate(String user, String pass);

}
