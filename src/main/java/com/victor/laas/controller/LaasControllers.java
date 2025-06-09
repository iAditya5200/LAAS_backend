
package com.victor.laas.controller;


import com.victor.laas.bo.LaasBO;
import com.victor.laas.entity.Student;
import com.victor.laas.entity.User;
import com.victor.laas.entity.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/laas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LaasControllers {
    @Autowired
    private LaasBO service ;
   @GetMapping("/health-check")
    public String healthCheck() {
        return "randwa ankit" ;

    }
    @PostMapping("/registerUser")
    public ResponseEntity<String> registerUser(@RequestBody User user) {

       String status = service.registerUser(user);
       
       return new ResponseEntity<>(status , HttpStatus.OK) ;
       
      
    }
    
    @PostMapping("/addStudent")
    public ResponseEntity<String> registerUser(@RequestBody Student student) {

       String status = service.addStudent(student);
       
       return new ResponseEntity<>(status , HttpStatus.OK) ;
     
    }
    
    @PostMapping("/addAdmin")
    public ResponseEntity<String> registerUser(@RequestBody Admin admin) {

       String status = service.addAdmin(admin);
       return new ResponseEntity<>(status,HttpStatus.OK) ;
       
//       else {
//    	   return new ResponseEntity<>(status , HttpStatus.BAD_REQUEST) ;
//       }
    }

}