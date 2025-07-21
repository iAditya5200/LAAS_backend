package com.victor.laas.dao;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.victor.laas.dao.LaasDAO;
import com.victor.laas.entity.Admin;
import com.victor.laas.entity.Student;
import com.victor.laas.entity.Users;

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
	


    public String registerUser(Users users)
    { ObjectMapper objectMapper = new ObjectMapper();
       Map<String, Object> response = new HashMap<>();
    	
    	try {
			String query = "INSERT INTO laasdb.user (email , password,role) VALUES (?,?,?);" ;
			Object[] params = {users.getEmail() ,users.getPassword() ,users.getRole()}; 
			
			int count = jdbcTemplate.update(query, params);
			
			if(count > 0)
			{
				 response.put("empid", users.getEmail());
		 	        response.put("message", "status: created");  
			}
			else {
				 response.put("empid", users.getEmail());
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
    
    public Users checkUser(String username) {
        try {
            String query = "SELECT * FROM laasdb.user WHERE email = ?;";
            Object[] params = {username};

            List<Users> usersList = jdbcTemplate.query(
                query,
                params,
                new BeanPropertyRowMapper<>(Users.class)
            );

            return usersList.isEmpty() ? null : usersList.get(0);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public String getStudentDetails(String username) {
    	String command = "SELECT * FROM laasdb.student WHERE email = ? ";
    	Object[] param = { username };

    	try {
    		ObjectMapper map = new ObjectMapper();
    		List<Map<String, Object>> response = jdbcTemplate.queryForList(command, param);
    		return map.writeValueAsString(response);

    	} catch (Exception e) {
    		throw new RuntimeException("Failed to retrieve student data for email " + username , e);
    	}

    }
}
