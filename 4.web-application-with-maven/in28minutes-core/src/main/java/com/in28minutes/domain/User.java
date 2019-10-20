package com.in28minutes.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = "findUserByEmail", query = "SELECT u FROM User u where u.email = :p_email"),
		@NamedQuery(name = "findUserByEmailAndPassword", query = "SELECT u FROM User u where u.email = :p_email and u.password = :p_password") })
public class User implements Serializable {

	private static final double PI = 3.13;

	private static final long serialVersionUID = -4230215154003125220L;

	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private String password;
	private String email;

	public User() {
	}

	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, items=%s, name=%s, email=%s, password=%s]", id, "", name, email, password);
	}

	public void CalculateSum(int[] numbers) {
		int result = 0;
		for (int i = 0; i < numbers.length; i++) {
			result = result + numbers[i];
		}

		double calVal = result * PI * 1 * 2;
		System.out.println("calculed val " + calVal);

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
}
