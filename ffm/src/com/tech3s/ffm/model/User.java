package com.tech3s.ffm.model;

public class User {
	
	private int id;
	private int age;
	private String email;
	private String name;
	private String password;
	private String position;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", age=" + age + ", email=" + email + ", name=" + name + ", password=" + password
				+ ", position=" + position + "]";
	}
	
	
	
}
