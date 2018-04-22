package com.starter.template.user.utils;

import java.util.Locale;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.starter.template.mail.model.EmailContainer;
import com.starter.template.mail.service.EmailSenderUtil;
import com.starter.template.user.model.User;

public class RegistrationUtils {
	@Autowired
	private EmailSenderUtil emailSender;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private Environment env;
    
	public void sendRegNotification(User user, Locale locale) throws MessagingException {
        final Context ctx = new Context(locale);
        ctx.setVariable("name", user.getFirstName());
        final String content = templateEngine.process(env.getProperty("email.template.user.registration.success"), ctx);
        
        EmailContainer emailContent = new EmailContainer();
        emailContent.setFrom(env.getProperty("email.default.from.address"));
        emailContent.setTo(user.getEmailId());
        emailContent.setContent(content);
        emailContent.setSubject(env.getProperty("email.template.subject.user.registration"));
        
        emailSender.sendsimpleMail(emailContent);
    }
}
