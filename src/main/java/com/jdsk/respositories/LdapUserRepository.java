package com.jdsk.respositories;

import java.util.List;

import javax.naming.Name;

import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jdsk.entities.LdapUser;

@Repository
//public interface LdapUserRepository extends CrudRepository<LdapUser, Name> {
public interface LdapUserRepository extends LdapRepository<LdapUser> {
	LdapUser findByUsername(String username);
    LdapUser findByUsernameAndPassword(String username, String password);
    List <LdapUser> findByUsernameLikeIgnoreCase(String username);
    List<LdapUser> findAll();
}
