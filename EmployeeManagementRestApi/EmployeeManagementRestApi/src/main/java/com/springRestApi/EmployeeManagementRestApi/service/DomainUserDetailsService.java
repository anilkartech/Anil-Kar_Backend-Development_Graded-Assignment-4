package com.springRestApi.EmployeeManagementRestApi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.springRestApi.EmployeeManagementRestApi.model.DomainUserDetails;
import com.springRestApi.EmployeeManagementRestApi.model.User;
import com.springRestApi.EmployeeManagementRestApi.repository.UserRepository;

@Service
public class DomainUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = this.userRepository.findByUsername(username);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			System.out.println("use:" + optionalUser.get());
			return new DomainUserDetails(user);

		}
		throw new UsernameNotFoundException("bad credentials");
	}
}
