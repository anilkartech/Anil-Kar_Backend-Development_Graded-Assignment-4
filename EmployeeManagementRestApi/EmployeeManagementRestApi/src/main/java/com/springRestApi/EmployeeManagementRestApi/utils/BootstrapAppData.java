package com.springRestApi.EmployeeManagementRestApi.utils;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.springRestApi.EmployeeManagementRestApi.model.Role;
import com.springRestApi.EmployeeManagementRestApi.model.User;
import com.springRestApi.EmployeeManagementRestApi.repository.UserRepository;

@Component
public class BootstrapAppData {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void insertUsers(ApplicationReadyEvent event) {
		User firstUser = new User();
		firstUser.setUsername("Anil");
		firstUser.setPassword(this.passwordEncoder.encode("welcome"));

		User secondUser = new User();
		secondUser.setUsername("Somya");
		secondUser.setPassword(this.passwordEncoder.encode("welcome"));

		Role userRole = new Role();
		userRole.setName("USER");

		Role adminRole = new Role();
		adminRole.setName("ADMIN");

		List<Role> firstUserRoles = firstUser.getRoles();
		firstUserRoles.add(userRole);

		List<Role> secondUserRoles = secondUser.getRoles();
		secondUserRoles.add(userRole);
		secondUserRoles.add(adminRole);

		this.userRepository.saveAndFlush(firstUser);
		this.userRepository.saveAndFlush(secondUser);

	}
}
