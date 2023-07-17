package com.test.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class users {
	@Id
	@GeneratedValue
	private int id ; 
	private String username  ; 
	private String passord ;
	private String roles; 
	
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getuserName () {
		return username ;
	}
	public void setusername(String userName ) {
		this.username  = userName ;
	}
	public String getPassord() {
		return passord;
	}
	public void setPassord(String passord) {
		this.passord = passord;
	} 
	
	
	

}
