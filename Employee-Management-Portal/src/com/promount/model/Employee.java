package com.promount.model;

import com.mysql.cj.jdbc.Blob;

public class Employee {	
	private int id;
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	private String fullName;
	private Long phone;
	private String tech;
	private String profilePhotoUrl;
	private String userName;
	private String password;
	private String confirmPassword;
	
	public Employee(String fullName, Long phone, String tech, String profilePhotoUrl, String userName,
			String password) {
		super();
		this.fullName = fullName;
		this.phone = phone;
		this.tech = tech;
		this.profilePhotoUrl = profilePhotoUrl;
		this.userName = userName;
		this.password = password;
	}
	
	

	public Employee(Integer id, String fullName, Long phone, String tech, String profilePhotoUrl, String userName) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.phone = phone;
		this.tech = tech;
		this.profilePhotoUrl = profilePhotoUrl;
		this.userName = userName;
	}



	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getTech() {
		return tech;
	}

	public void setTech(String tech) {
		this.tech = tech;
	}

	public String getProfilePhotoUrl() {
		return profilePhotoUrl;
	}

	public void setProfilePhotoUrl(String profilePhotoUrl) {
		this.profilePhotoUrl = profilePhotoUrl;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "Employee [ fullName=" + fullName + ", tech=" + tech + ", phone=" + phone
				+ ", profilePhotoUrl=" + profilePhotoUrl + ", userName=" + userName + ", password=" + password + "]";
	}
		
}
