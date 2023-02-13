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

	   @Value("${ldap.urls}")
	    private String ldapUrls;

	    @Value("${ldap.base.dn}")
	    private String ldapBaseDn;

	    @Value("${ldap.username}")
	    private String ldapSecurityPrincipal;

	    @Value("${ldap.password}")
	    private String ldapPrincipalPassword;

	    @Value("${ldap.user.dn.pattern}")
	    private String ldapUserDnPattern;

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
//  @Autowired
//  public void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth
//      .ldapAuthentication()
//      .userDnPatterns("ou=people")
//        .groupSearchBase("ou=people")
//        .contextSource()
//          .url("ldap://localhost:10389/dc=example,dc=com")
//          .managerDn("uid=admin,ou=system")
//          .managerPassword("secret")
//          .and()
//        .passwordCompare()
//          .passwordEncoder(new BCryptPasswordEncoder())
//          .passwordAttribute("userPassword");
//  }
//
  @Autowired
  void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth
              .ldapAuthentication().contextSource(ldapConfig.contextSource())
              .userDnPatterns("ou=people,dc=example,dc=com");
              //.userDnPatterns("uid={0}");
    
  }

}
