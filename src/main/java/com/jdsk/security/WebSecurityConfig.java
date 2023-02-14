package com.jdsk.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.jdsk.config.LdapConfig;

@Configuration
public class WebSecurityConfig {

	@Autowired
	LdapConfig ldapConfig;

	    

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeHttpRequests()
        .anyRequest().permitAll();
    return http.build();
  }

//
  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
      return http.getSharedObject(AuthenticationManagerBuilder.class)
              .build();
  }
  @Autowired
  void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth
              .ldapAuthentication().contextSource(ldapConfig.contextSource())
              .userDnPatterns("uid={0}");
              
  }
  //.userDnPatterns("ou=people,dc=example,dc=com");
  //.userSearchFilter("uid=userName")
  //.userSearchBase("uid={0},ou=people,dc=example,dc=com")
  //.userSearchBase("ou=people,dc=example,dc=com")
  //..userDnPatterns("uid={0},ou=people,dc=example,dc=com");

}
