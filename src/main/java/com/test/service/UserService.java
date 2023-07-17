package com.test.service;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.entities.users;
import com.test.repo.userrepos;
import com.test.securite.MyUserDetails;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private userrepos userRepository;

	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<users> user = userRepository.findByUsername(userName);

		user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

		return user.map(MyUserDetails::new).get();
	}

	public void addUser(users user) {
		user.setPassord(bCryptPasswordEncoder().encode(user.getPassord()));
		userRepository.save(user);
	}

	public List<users> getAllUsers() {

		return userRepository.findAll();
	}

	public Optional<users> findByUserName(String userName) {

		return userRepository.findByUsername(userName);
	}

}