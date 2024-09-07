package com.irctc.irctc.bean;


import java.util.Date;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String password;
	private Date dob;
	private String username;
	private String emailId;
	private String role;
	public User(@Nonnull String password, Date dob, @Nonnull String username, String emailId, @Nonnull String role) {
		super();
		this.password = password;
		this.dob = dob;
		this.username = username;
		this.emailId = emailId;
		this.role = role;
	}
	
}



