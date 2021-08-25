package com.day.service;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import com.day.exception.FindException;
import com.day.dao.UserDAO;
import com.day.dto.User;


public class UserService {
	
	private UserDAO dao;
	
	private static UserService service = new UserService();
	
	public static UserService getInstance() {
		return service;
	}
	
	public UserService() {	
		
		Properties env = new Properties();
		
		try {
			
			env.load(new FileInputStream("classes.prop"));
			String className = env.getProperty("userDAO");
			Class c = Class.forName(className);
			dao = (UserDAO) c.newInstance();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public Map<String, User> findAll() throws FindException{
		return dao.selectAll();
	}
	
	public void singUp(String id, String pw) throws FindException{
		dao.insertInfo(id, pw);
	}
	
	public void backUp(User userInfo) throws FindException{
		dao.insertAllInfo(userInfo);
	}
	
	public ArrayList<String> getIdList() throws FindException {
		return dao.getIdList();
	}
	
	public ArrayList<String> getPwList() throws FindException {
		return dao.getPwList();
	}
}
