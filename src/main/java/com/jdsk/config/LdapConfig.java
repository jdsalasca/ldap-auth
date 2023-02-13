package com.jdsk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

import jakarta.annotation.Priority;

@Configuration
@Priority(value = 1)
@EnableLdapRepositories
public class LdapConfig {
	@Bean
	@Autowired
	public LdapContextSource contextSource() {
	    LdapContextSource contextSource = new LdapContextSource();
	    contextSource.setUrl("ldap://localhost:10389");
	    contextSource.setBase("ou=people,dc=example,dc=com");
	    contextSource.setUserDn("uid=admin,ou=system");
	    contextSource.setPassword("secret");
	    return contextSource;
	}
	@Bean
	LdapTemplate ldapTemplate() {
	    return new LdapTemplate(contextSource());
	}
	

//	@Bean
//	@Autowired
//	public LdapContextSource contextSource() {
//	    LdapContextSource contextSource = new LdapContextSource();
//
//	    contextSource.setUrl("ldap://localhost:10636");
//	    contextSource.setBase("dc=example,dc=com");
//	    contextSource.setUserDn("uid=admin,ou=system");
//	    contextSource.setPassword("secret");
//	    return contextSource;
//	}
//	@Bean
//	LdapTemplate ldapTemplate() {
//	    return new LdapTemplate(contextSource());
//	}

//	@Bean
//	ContextSource contextSource(UnboundIdContainer container) {
//		return new DefaultSpringSecurityContextSource("ldap://localhost:10389/dc=example,dc=com");
//	}
}
