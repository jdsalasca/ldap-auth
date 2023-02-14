package com.jdsk.entities;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entry(base = "ou=people", objectClasses = { "inetOrgPerson", "organizationalPerson", "person", "top" })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class LdapUser {
	@Id
	@JsonIgnore
	Name dn;
	@DnAttribute(value = "uid", index = 0)
	private @Attribute(name = "uid") String uid;
	@NotNull(message = "firstName es obligatorio")
	@Attribute(name = "cn")
	String firstName;
	@NotNull(message = "lastName es obligatorio")
	@JsonProperty("lastName")
	@Attribute(name = "sn")
	String sn;
	@NotNull(message = "documentNumber es obligatorio")
	@JsonProperty("documentNumber")
	Long uniqueIdentifier;
	// @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotNull(message = "password es obligatorio")
	@Attribute(name = "userPassword") String password;
	@NotNull(message = "description es obligatoria")
	String description;
	@NotNull(message = "photo es obligatorio")
	private String photo;
	@NotNull(message = "email es obligatorio")
	@Attribute(name = "mail") private String email;
//	@NotNull(message = "name es obligatorio")
//	private String name;
//	@NotNull(message = "documentNumber es obligatorio")
//	@Attribute(name = "uidNumber")
//	private String documentNumber;
	//@NotNull(message = "documentTypeId es obligatorio")
	//private int documentTypeId =1;

	//private String expirtyDate;
	//private int statusId = 1;
	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

}
