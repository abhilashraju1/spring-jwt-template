package com.starter.template.user.service;

import com.starter.template.user.model.User;

public interface UserService {
	boolean createUser(User user);
	User getUserByUserName(String userName);
}
