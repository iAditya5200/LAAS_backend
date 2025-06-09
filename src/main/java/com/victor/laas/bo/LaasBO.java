package com.victor.laas.bo;

import com.victor.laas.dao.LaasDAO;
import com.victor.laas.entity.Admin;
import com.victor.laas.entity.Student;
import com.victor.laas.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class LaasBO {
   @Autowired
    private LaasDAO dao ;
    public String registerUser(User user)
    {
        return dao.registerUser(user);
    }
    public String addStudent(Student student)
    {
        return dao.addStudent(student);
    }
    
    public String addAdmin(Admin admin )
    {
        return dao.addAdmin(admin);
    }
}