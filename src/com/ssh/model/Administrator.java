package com.ssh.model;

public class Administrator {
	private int id; //����Ա���
	private String name;  //����Ա����
	private String password;  //����Ա����
	
	public Administrator(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
}
