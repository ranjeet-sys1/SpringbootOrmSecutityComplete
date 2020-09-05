package com.app.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.model.User;
import com.app.repo.UserRepository;
import com.app.service.IUserService;
@Service
public class UserServiceImpl implements IUserService ,UserDetailsService {
	@Autowired
	private UserRepository repo; //HAS-A
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public Integer saveUser(User user) {
		String password=user.getPassword();
		String encodePassword=encoder.encode(password);
		user.setPassword(encodePassword);
		user = repo.save(user);
		return user.getId();
	}
/*
 * this method is of UserDetailsService
 * */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, DataAccessException {
		Optional<User> findByEmail = repo.findByEmail(email);
		if(!findByEmail.isPresent())
			throw new  UsernameNotFoundException("User not found");
		User user=findByEmail.get();//model class User Object but below is Spring f/w User class Object
		
		return new org.springframework.security.core.userdetails.
				User(
						email, 
						user.getPassword(), 
						user.getRoles()
						.stream()
						.map(role -> new SimpleGrantedAuthority(role))
						.collect(Collectors.toSet())

						); //spring security user
			
			
	

}
}