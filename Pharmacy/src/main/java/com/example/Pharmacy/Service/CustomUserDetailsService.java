package com.example.Pharmacy.Service;

import com.example.Pharmacy.Model.AppUser;
import com.example.Pharmacy.Repository.CustomeUserDetails;
import com.example.Pharmacy.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
@Autowired
private UserRepo userRepo;

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	System.out.println("Looking up user: " + username);
	AppUser user=userRepo.findByUsername(username)
		.orElseThrow(() -> new UsernameNotFoundException("User not found"));

	return new CustomeUserDetails(user);


}



}
