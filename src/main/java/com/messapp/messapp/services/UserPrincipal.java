package com.messapp.messapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.messapp.messapp.entities.UserEntity;
import com.messapp.messapp.enums.Role;



public class UserPrincipal implements UserDetails{

	private UserEntity users;
	
	
	public UserPrincipal(UserEntity users) {
		super();
		this.users = users;
	}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			if(users.getRole() == Role.PERSON)
			{
				List<SimpleGrantedAuthority> authorities = new ArrayList<>();
			    authorities.add(new SimpleGrantedAuthority("PERSON"));
			    //authorities.add(new SimpleGrantedAuthority("ADMIN"));
			    return authorities;
			}
			List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		    //authorities.add(new SimpleGrantedAuthority("PERSON"));
		    authorities.add(new SimpleGrantedAuthority("ADMIN"));
		    return authorities;
		    
		}
	
	@Override
	public String getPassword() {
		
		return users.getPassword();
	}

	@Override
	public String getUsername() {
		
		return users.getEmail();
	}

}
