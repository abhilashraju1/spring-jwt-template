package com.starter.template.validation.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.starter.template.user.dao.UserRepository;
import com.starter.template.user.model.User;

public class UserByEmailIdValidator implements ConstraintValidator<DoesEmailIdExist, String> {
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean status = false;
		if(value == null || value.trim().length() <= 0) {
			status = true;
		}else {
			User userByEmailId = userRepo.findByUserName(value);
			status = !(userByEmailId != null && value.equalsIgnoreCase(userByEmailId.getUserName()));
		}
		return status;
	}

}
