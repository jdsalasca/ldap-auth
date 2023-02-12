package com.jdsk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.config.ldap.EmbeddedLdapServerContextSourceFactoryBean;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.server.UnboundIdContainer;

@Configuration
public class LdapConfig {
	
	@Bean
	LdapContextSource contextSource() {
	    LdapContextSource contextSource = new LdapContextSource();
	    
	    contextSource.setUrl("ldap://localhost:10636");
	    contextSource.setBase("dc=example,dc=com");
	    contextSource.setUserDn("ou=users");
	    contextSource.setPassword("secret");
	    
	    return contextSource;
	}
	@Bean
	LdapTemplate ldapTemplate() {
	    return new LdapTemplate(contextSource());
	}

//	@Bean 
//	ContextSource contextSource(UnboundIdContainer container) {
//		return new DefaultSpringSecurityContextSource("ldap://localhost:10389/dc=example,dc=com");
//	}
}
