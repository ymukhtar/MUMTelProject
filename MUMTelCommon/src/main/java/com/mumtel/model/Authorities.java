package com.mumtel.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 
 * @author ymukhtar
 * @author aTariq
 */
@Entity
public class Authorities implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5559181405115205211L;
	@Id
	@GeneratedValue
	private long user_role_id;
	@ManyToOne
	@JoinColumn(name = "username")
	private Users user;
	@Column(length = 45)
	private String role;
	
	public Authorities() {
		super();
	}
	public Authorities(Users user, String role) {
		super();
		this.user = user;
		this.role = role;
		this.user.addAuthority(this);
	}
	public long getUser_role_id() {
		return user_role_id;
	}
	public void setUser_role_id(long user_role_id) {
		this.user_role_id = user_role_id;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Authorities [user_role_id=" + user_role_id + ", user=" + user
				+ ", role=" + role + "]";
	}
}