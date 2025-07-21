package com.victor.laas.bo;

import com.victor.laas.dao.LaasDAO;
import com.victor.laas.entity.Admin;
import com.victor.laas.entity.Student;
import com.victor.laas.entity.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class LaasBO {
   @Autowired
    private LaasDAO dao ;
   @Autowired
   private JWTservice jwt ;
   @Autowired
   private AuthenticationManager authManager ;
   
   private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12) ;
   
    public String registerUser(Users users)
    { users.setPassword(encoder.encode(users.getPassword()));
        return dao.registerUser(users);
    }
    public String addStudent(Student student)
    {
        return dao.addStudent(student);
    }
    
    public String addAdmin(Admin admin )
    {
        return dao.addAdmin(admin);
    }
    public String getStudentDetails(String username)
    {
        return dao.getStudentDetails(username);
    }
	public String login(Users users) {
		Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(users.getEmail() , users.getPassword())) ;
		if(auth.isAuthenticated()) {
			return jwt.generateToken(users.getEmail()) ;
		}
		return "failed" ;
	}
}