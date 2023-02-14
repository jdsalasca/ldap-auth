package com.jdsk.services;


import java.util.List;

import javax.naming.Name;
import javax.naming.directory.DirContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.LikeFilter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.SearchScope;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.config.ldap.LdapPasswordComparisonAuthenticationManagerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.jdsk.config.LdapConfig;
import com.jdsk.entities.LdapUser;
import com.jdsk.respositories.LdapUserRepository;
import com.jdsk.security.userDetails;
import com.jdsk.utils.DefaultResponse;
@Service
public class LdapServiceImpl implements LdapService {
	
    @Autowired
    private LdapUserRepository ldapUserRepository;
    
    private LdapUtils ldapUtils;
    @Autowired
    private LdapConfig ldapConfig;
    
    @Autowired
    private LdapTemplate ldapTemplate;
    

	@Override
	public LdapUser search(String username) {
		LdapUser entity  = ldapUserRepository.findByUid(username);
		return entity;
	}

	@Override
	public List<LdapUser> getAll() {
		return ldapUserRepository.findAll();
	}
	@Override
	public ResponseEntity<DefaultResponse<Boolean>> authenticate(String user, String pass) {
		LdapUser userEntity = ldapUserRepository.findByUid(user);
		AndFilter filterA= new AndFilter();
		filterA.and(new EqualsFilter("objectClass", "person"));
		filterA.and(new EqualsFilter("uid", user));
		
		 boolean authenticated = ldapTemplate.authenticate("",filterA.encode(), pass);
	    return   DefaultResponse.onThrow200Response(List.of(authenticated));
	}
	@Override
	public ResponseEntity<DefaultResponse<LdapUser>> create(LdapUser  ldapUser, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return DefaultResponse.onThrow400ResponseBindingResult(bindingResult);
		}
		if (ldapUserRepository.findByUid(ldapUser.getUid()) !=null) {
			return DefaultResponse.onThrow400ResponseTypeInfo("El usuario ya existe");
		}
		Name dn = LdapNameBuilder.newInstance()
                .add("ou", "people")
                .add("uid", ldapUser.getUid())
                .build();
		//ldapUser.setDn(LdapUtils.emptyLdapName());
		//ldapUser.setDn(dn);
	    return  DefaultResponse.onThrow200Response(List.of(ldapUserRepository.save(ldapUser)));		    
	}
	//LdapUser newUser = new User(username,digestSHA(password));
	//ldapUser.setDn(LdapUtils.emptyLdapName());

}
