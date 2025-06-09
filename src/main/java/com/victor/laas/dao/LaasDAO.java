package com.victor.laas.dao;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.victor.laas.dao.LaasDAO;
import com.victor.laas.entity.Admin;
import com.victor.laas.entity.Student;
import com.victor.laas.entity.User;

@Component
public class LaasDAO {

//	private transient static JdbcTemplate jdbcTemplate;
//	@Autowired
//	private transient DataSource datasource; 
//	@Autowired
//	public LaasDAO(DataSource dataSource) throws SQLException {
//		
//		this.datasource = dataSource;
//		jdbcTemplate = new JdbcTemplate(this.datasource);
//	}\
	
	
//	private transient static JdbcTemplate jdbcTemplate;
//	@Autowired
//	private transient DataSource datasource; 
//	@Autowired
//	public LaasDAO(DataSource dataSource) throws SQLException {
//		this.datasource = dataSource;
//		jdbcTemplate = new JdbcTemplate(this.datasource);
//	}
	
	@Autowired
	private JdbcTemplate jdbcTemplate ;
	


    public String registerUser(User user)
    { ObjectMapper objectMapper = new ObjectMapper();
       Map<String, Object> response = new HashMap<>();
    	
    	try {
			String query = "INSERT INTO laasdb.user (email , password,role) VALUES (?,?,?);" ;
			Object[] params = {user.getEmail() ,user.getPassword() ,user.getRole()}; 
			
			int count = jdbcTemplate.update(query, params);
			
			if(count > 0)
			{
				 response.put("empid", user.getEmail());
		 	        response.put("message", "status: created");  
			}
			else {
				 response.put("empid", user.getEmail());
		 	        response.put("message", "status: not created");
			}
			 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	try {
	        return objectMapper.writeValueAsString(response);
	    } catch (Exception e) {
	        throw new RuntimeException("Error while generating JSON response: " + e.getMessage());
	    }
      
    }
    
    public String addStudent(Student student)
    {ObjectMapper objectMapper = new ObjectMapper();
    Map<String, Object> response = new HashMap<>();
    	
    	try {
			String query = "INSERT INTO laasdb.student ( roll ,"
					+ "email ,"
					+ "department,"
					+ "name,"
					+ "phone,"
					+ "attendance) VALUES (?,?,?,?,?,?);" ;
			Object[] params = {student.getRoll() ,student.getEmail(),student.getDepartment() ,student.getName(),student.getPhone(),student.getAttendance()}; 
			
			int count = jdbcTemplate.update(query, params);
			
			if(count > 0)
			{     response.put("empid", student.getRoll());
		 	        response.put("message", "status: created");  
			}
			else {
				 response.put("empid", student.getRoll());
	 	        response.put("message", "status: not created");  
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	try {
	        return objectMapper.writeValueAsString(response);
	    } catch (Exception e) {
	        throw new RuntimeException("Error while generating JSON response: " + e.getMessage());
	    }
    }
    
    public String addAdmin(Admin admin)
    {  ObjectMapper objectMapper = new ObjectMapper();
       Map<String, Object> response = new HashMap<>();
    	try {
			String query = "INSERT INTO laasdb.admin (empid ,"
					+ "	email ,"
					+ "	department,"
					+ "	 post,"
					+ "	phone,"
					+ "name) VALUES (?,?,?,?,?,?);" ;
			Object[] params = {admin.getEmpid(),admin.getEmail(),admin.getDepartment() ,admin.getPost(),admin.getPhone(),admin.getName()}; 
			
			int count = jdbcTemplate.update(query, params);
			
			if(count > 0)
			{     response.put("empid", admin.getEmpid());
		 	        response.put("message", "status: created");  
			}
			else {
				 response.put("empid", admin.getEmpid());
	 	        response.put("message", "status: not created");  
			}
			 try {
			        return objectMapper.writeValueAsString(response);
			    } catch (Exception e) {
			        throw new RuntimeException("Error while generating JSON response: " + e.getMessage());
			    }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        return "status";
    }
}
