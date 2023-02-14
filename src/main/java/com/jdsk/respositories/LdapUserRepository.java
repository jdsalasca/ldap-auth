package com.jdsk.respositories;

import java.util.List;

import javax.naming.Name;

import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jdsk.entities.LdapUser;

@Repository
//public interface LdapUserRepository extends CrudRepository<LdapUser, Long> {
public interface LdapUserRepository extends LdapRepository<LdapUser> {
	
    LdapUser findByUidAndSn(String username, String password);
    List <LdapUser> findByUidLikeIgnoreCase(String username);
    List<LdapUser> findAll();
	LdapUser findByUid(String user);
	LdapUser findByUidAndPassword(String user, String hashpw);
}
