package com.starter.template.config.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.starter.template.mail.service.EmailSenderUtil;
import com.starter.template.user.utils.RegistrationUtils;

@Configuration
public class DomainBeanConfig {
	@Bean
	public RegistrationUtils getRegistrationUtils() {
		return new RegistrationUtils();
	}
	
	@Bean
	public EmailSenderUtil emailSenderUtility() {
		return new EmailSenderUtil();
	}
}
