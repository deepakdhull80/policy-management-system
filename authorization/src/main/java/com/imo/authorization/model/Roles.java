package com.imo.authorization.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Roles {

	@Id @GeneratedValue
	private int roleId;
	private String role;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private User user;
	
	public Roles() {
		super();
	}
	public Roles(String role) {
		super();
		this.role = role;
	}
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Roles [roleId=" + roleId + ", role=" + role + "]";
	}
	
	
	
}
