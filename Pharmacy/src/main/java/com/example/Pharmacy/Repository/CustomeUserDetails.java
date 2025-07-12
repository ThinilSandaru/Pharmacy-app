package com.example.Pharmacy.Repository;

import com.example.Pharmacy.Model.AppUser;
import com.example.Pharmacy.Service.CustomUserDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomeUserDetails implements UserDetails {

private  final AppUser user;

public CustomeUserDetails(AppUser user){
	this.user=user;
}

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
}

@Override
public String getPassword() {
	return user.getPassword();
}

@Override
public String getUsername() {
	return user.getUsername();
}

@Override
public boolean isEnabled() {
	return user.isEnabled();
}
}
