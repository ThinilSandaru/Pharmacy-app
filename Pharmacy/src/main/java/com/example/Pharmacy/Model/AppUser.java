package com.example.Pharmacy.Model;


import jakarta.persistence.*;

@Entity
@Table(name="User")
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private  String username;

	private  String email;

	@Enumerated(EnumType.STRING)
	private Role role;

	private  String password;


	private boolean enabled;


public String getPassword() {
	return password;
}

public String getUsername() {
	return username;
}

public int getId() {
	return id;
}

public Role getRole() {
	return role;
}

public boolean isEnabled() {
	return enabled;
}

public String getEmail() {
	return email;
}

public void setPassword(String password) {
	this.password = password;
}

public void setUsername(String username) {
	this.username = username;
}

public void setEmail(String email) {
	this.email = email;
}

public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}

public void setId(int id) {
	this.id = id;
}

public void setRole(Role role) {
	this.role = role;
}
}
