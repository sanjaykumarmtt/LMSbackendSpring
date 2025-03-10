package com.LMS.LMSBsckend.Login.Entity;

public class LOginDto {
	
	private String name;
	private String Username;
	private String Password;
	private String role;
	private String otp;
	
	public LOginDto() {
		super();
	}
	public LOginDto(String name, String username, String password, String role, String otp) {
		super();
		this.name = name;
		Username = username;
		Password = password;
		this.role = role;
		this.otp = otp;
	}


	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
}
