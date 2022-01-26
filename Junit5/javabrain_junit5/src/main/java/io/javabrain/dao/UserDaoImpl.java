package io.javabrain.dao;

public class UserDaoImpl implements UserDao {

	@Override
	public String findNameById(int Id) {
		System.out.println("findNameById(int Id) called");
		return "Sagar";
	}

	@Override
	public String findEmailById(int Id) {
		System.out.println("findEmailById(int Id) called");
		return "Sagarrao1@gmail.com";
	}

}
