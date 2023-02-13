package com.jdsk.entities;


import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//@Entry(objectClasses = { "person", "top" }, base="ou=people")
@Entry(base = "ou=people", objectClasses = { "inetOrgPerson","organizationalPerson" ,"person", "top" })
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public final class LdapUser {
    @Id
    @JsonIgnore
    Name dn;
    
    private @Attribute(name = "uid") String uid;
    @NotNull(message = "username es obligatorio")
    @Attribute(name = "cn") String username;
    @NotNull(message = "lastName es obligatorio")
    @JsonProperty("lastName")
    @Attribute(name = "sn") String sn;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "password es obligatorio")
     @Attribute(name = "userPassword") String password;
    @NotNull(message = "description es obligatoria")
     String description;
     
     @Attribute(name="firstName")
     private String firstName;


}
