package com.jdsk.services;

import java.util.List;

import javax.naming.Name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.jdsk.entities.LdapUser;
import com.jdsk.respositories.LdapUserRepository;
import com.jdsk.utils.DefaultResponse;
@Service
public class LdapServiceImpl implements LdapService {
	
    @Autowired
    private LdapUserRepository ldapUserRepository;
    
    private LdapUtils ldapUtils;

	@Override
	public LdapUser search(String username) {
		LdapUser entity  = ldapUserRepository.findByUsername(username);
		return entity;
	}

	@Override
	public List<LdapUser> getAll() {
		// TODO Auto-generated method stub
		return ldapUserRepository.findAll();
	}
	public Boolean authenticate(String u, String p) {
	    return ldapUserRepository.findByUsernameAndPassword(u, p) != null;
	}
	@Override
	public ResponseEntity<DefaultResponse<LdapUser>> create(LdapUser  ldapUser, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return DefaultResponse.onThrow400ResponseBindingResult(bindingResult);
		}
	    //LdapUser newUser = new User(username,digestSHA(password));
		Name dn = LdapNameBuilder.newInstance()
                //.add("ou", "people")
                .add("uid", ldapUser.getUsername())
                .build();
		ldapUser.setDn(dn);
		//ldapUser.setDn(LdapUtils.emptyLdapName());
	    return  DefaultResponse.onThrow200Response(List.of(ldapUserRepository.save(ldapUser)));	
	    
	}

}
