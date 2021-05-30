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
			System.out.println("test 1 ");
			env.load(new FileInputStream("classes.prop"));
			System.out.println("test 2 ");
			String className = env.getProperty("userDAO");
			System.out.println("test 1 ");
			Class c = Class.forName(className);
			System.out.println("test 1 ");
			dao = (UserDAO) c.newInstance();
			System.out.println("test 2 ");
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
