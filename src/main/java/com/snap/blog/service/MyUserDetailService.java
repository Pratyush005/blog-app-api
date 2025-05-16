package com.snap.blog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.snap.blog.entities.User;
import com.snap.blog.exception.ResourceNotFoundException;
import com.snap.blog.repository.UserRepo;
import com.snap.blog.security.UserPrincipal;

/**
 * It is used to customize the UserDetailsServiceFilters
 */
@Service
public class MyUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;

	/**
	 * Finds users by username from the database and return the userDetail.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepo.findByuserName(username);
		
		if(user == null) {
			throw new ResourceNotFoundException("user", "username", 3);
		}
		return new UserPrincipal(user);
	}

}
