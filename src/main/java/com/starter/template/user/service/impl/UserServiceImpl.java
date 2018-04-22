package com.starter.template.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.starter.template.user.dao.UserRepository;
import com.starter.template.user.dao.UserRolesRepository;
import com.starter.template.user.model.Role;
import com.starter.template.user.model.User;
import com.starter.template.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired private PasswordEncoder passwordEncoder;
	@Autowired private UserRepository userRepo;
	@Autowired private UserRolesRepository userRolesRepo;
	@Autowired private Environment env;
	
	@Override
	public boolean createUser(User user) {
		List<Role> roles = userRolesRepo.findRolesByNames(env.getProperty("default.user.roles","ADMIN"));
		user.setRoles(roles);
		user.setStatus(env.getProperty("default.user.status","Y").charAt(0));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.createUser(user);
	}

	@Override
	public User getUserByUserName(String userName) {
		return userRepo.findByUserName(userName);
	}

}
