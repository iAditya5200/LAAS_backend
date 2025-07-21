package com.victor.laas.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collections;

public class UserDetailImpln implements UserDetails {

	private Users users ;
	
	public UserDetailImpln (Users users) {
		this.users = users ;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singleton( new SimpleGrantedAuthority("USER")) ;
	}
 
	@Override
	public String getPassword() {
		return users.getPassword();
	}

	@Override
	public String getUsername() {
		return users.getEmail();
	}
 
}
