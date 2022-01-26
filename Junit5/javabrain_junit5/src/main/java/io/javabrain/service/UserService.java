package io.javabrain.service;

import io.javabrain.dao.UserDao;

public class UserService {
	
	private UserDao userDao;

	
	public UserService() {		
	}
	
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}
	

	public String findNameById(int Id) {
		String result = userDao.findNameById(Id);		
		return result;
	}

	public String findEmailById(int Id) {
		String result =userDao.findEmailById(Id);
		return result;
	}
	
	public String doWork(String msg) {
		String formattedMsg = formatMsg(msg);
		return formattedMsg;
	}

	private String formatMsg(String msg) {
		return msg.toUpperCase();
	}
}
