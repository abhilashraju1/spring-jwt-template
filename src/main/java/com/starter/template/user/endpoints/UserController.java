package com.starter.template.user.endpoints;

import java.util.Locale;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.starter.template.user.model.User;
import com.starter.template.user.service.UserService;
import com.starter.template.user.utils.RegistrationUtils;

@RestController
public class UserController {
	
	@Autowired private UserService userService;
	@Autowired private RegistrationUtils regUtils;
	
	@GetMapping("/user/{userName}")
	public ResponseEntity<User> getUser(@PathVariable String userName, final Locale locale) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		User user = userService.getUserByUserName(userName);
		if(user != null) {
			status = HttpStatus.FOUND;
		}
		return new ResponseEntity<>(user,status);
	}
	
	@PostMapping("/user")
	public ResponseEntity<Object> register(@RequestBody @Valid User user, final Locale locale) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		if(userService.createUser(user)) {
			try {
				status = HttpStatus.CREATED;
				regUtils.sendRegNotification(user, locale);
			} catch (MessagingException e) {
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}
		return new ResponseEntity<>(status);
	}
}
