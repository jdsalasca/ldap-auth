package com.jdsk.services;

import java.util.List;

public interface LdapService {
	
	public List	<String> search(String username);
	public List	<String> getAll();

}
