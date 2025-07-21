
package com.victor.laas.controller;


import com.victor.laas.bo.JWTservice;
import com.victor.laas.bo.LaasBO;
import com.victor.laas.entity.Student;
import com.victor.laas.entity.Users;
import com.victor.laas.entity.Admin;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/laas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LaasControllers {
    @Autowired
    private LaasBO service ;
    @Autowired
    private JWTservice security ;
    private static final Logger log = Logger.getLogger(LaasControllers.class.getName());

   @GetMapping("/health-check")
    public String healthCheck() {
        return "LAAS is up" ;

    }
   @GetMapping("/student_detail")
   public ResponseEntity<String> studentData(@RequestHeader(name = "Authorization")String token) {
	   String response = null ;
	   try {
		   token = token.substring(7); // <- sanitize input
		   String username = security.extractUserName(token);
		   System.out.println(username + " " + "controller");
		    response = service.getStudentDetails(username);
	} catch (Exception e) {
		log.info(e.getMessage());
		response = "not Found" ;
		 return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST) ;
	}
	  
	   
	   return new ResponseEntity<>(response, HttpStatus.OK) ;

   }
    @PostMapping("/registerUser")
    public ResponseEntity<String> registerUser(@RequestBody Users users) {

       String status = service.registerUser(users);
       
       return new ResponseEntity<>(status , HttpStatus.OK) ;
       
      
    }
    @PostMapping("/login")
    public ResponseEntity<String> Login(@RequestBody Users users) {
    	 System.out.println(users) ;
       String status = service.login(users);
       System.out.println(users) ;
       return new ResponseEntity<>(status , HttpStatus.OK) ;
     
    }
    
    
    @PostMapping("/addStudent")
    public ResponseEntity<String> addStudnet(@RequestBody Student student) {

       String status = service.addStudent(student);
       
       return new ResponseEntity<>(status , HttpStatus.OK) ;
     
    }
    
    @PostMapping("/addAdmin")
    public ResponseEntity<String> addAdmin(@RequestBody Admin admin) {

       String status = service.addAdmin(admin);
       return new ResponseEntity<>(status,HttpStatus.OK) ;
       
//       else {
//    	   return new ResponseEntity<>(status , HttpStatus.BAD_REQUEST) ;
//       }
    }

}