package com.messapp.messapp.entities;

import com.messapp.messapp.enums.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity {
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	@Column(unique= true)
	private String email;
	@Column
	private String password ;
	@Enumerated(EnumType.STRING)
	private Role role;
	
	
	
	@OneToOne(mappedBy = "user" ,  cascade =CascadeType.ALL)
	private AdminEntity admin;
	
	
	@OneToOne(mappedBy = "user", cascade =CascadeType.ALL )
	private PersonEntity person;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public AdminEntity getAdmin() {
		return admin;
	}
	public void setAdmin(AdminEntity admin) {
		this.admin = admin;
	}
	public PersonEntity getPerson() {
		return person;
	}
	public void setPerson(PersonEntity person) {
		this.person = person;
	}
	
}
