package com.tcs.login.bean;

import javax.persistence.*;

@Entity
@Table(name="master")
public class Master {

	@Id
	@Column(name="user_id")
	private int id;
	
	@Column(name="password")
	private String password;
	
	@Column(name="role")
	private String role;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
