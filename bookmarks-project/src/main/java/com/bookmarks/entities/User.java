package com.bookmarks.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name ="USERS")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator= "seq_users_id")
	@SequenceGenerator(name="seq_users_id", sequenceName = "seq_users_id", allocationSize=10)
	@Column(name="USERID")
	private Long userId;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "LASTNAME")
	private String lastname;
	
	@Column(name = "EMAIL")
	private String emailAddress;
	
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "PASSWORD")
	private String password;
	
	public Long getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

		public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}	
}

