	package com.messapp.messapp.services;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.security.core.userdetails.UserDetails;
	import org.springframework.security.core.userdetails.UserDetailsService;
	import org.springframework.security.core.userdetails.UsernameNotFoundException;
	import org.springframework.stereotype.Service;
	
	import com.messapp.messapp.entities.UserEntity;
import com.messapp.messapp.exception.UserNotFoundException;
import com.messapp.messapp.jpaRepositories.UserRepository;
	
	
	@Service
	public class MyUserDetailsService  implements UserDetailsService{
	
		@Autowired
		private UserRepository userRepository;
		
		@Override
		public UserDetails loadUserByUsername(String  username) throws UsernameNotFoundException{
			   
				System.out.println("REACHED the my user details seivce , load user by username method");
				System.out.println(username);
				UserEntity users = userRepository.getUserFromEmail(username);
				if(users == null)
				{
					System.out.println("USER NOT FOUND");
					throw new UsernameNotFoundException("NO SUCH USER");
				}
				return new UserPrincipal(users);
		}
	
	}
