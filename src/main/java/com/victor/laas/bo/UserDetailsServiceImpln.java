package com.victor.laas.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.victor.laas.dao.LaasDAO;
import com.victor.laas.entity.Users;
import com.victor.laas.entity.UserDetailImpln;

@Service
public class UserDetailsServiceImpln implements UserDetailsService {
	
@Autowired
private LaasDAO dao ;
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Users users = dao.checkUser(username);
	if(users == null) {
		throw new UsernameNotFoundException("user not found" + username) ;
	}
	return new UserDetailImpln(users) ;
}
}
