package com.starter.template.user.dao;

import com.starter.template.user.model.User;

public interface UserRepository{
	boolean createUser(User user);
	User findByUserName(String userName);
}
