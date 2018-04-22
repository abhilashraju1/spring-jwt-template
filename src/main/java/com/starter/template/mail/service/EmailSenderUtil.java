package com.starter.template.mail.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.starter.template.mail.model.EmailContainer;

public class EmailSenderUtil {
	@Autowired
    private JavaMailSender mailSender;
	
	public boolean sendsimpleMail(EmailContainer emailContainer) {
		boolean status = true;
		final MimeMessage mimeMessage = mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        try {
			message.setSubject(emailContainer.getSubject());
			message.setFrom(emailContainer.getFrom());
			
			if(emailContainer.getTos() != null && !emailContainer.getTos().isEmpty()) {
				message.setTo(emailContainer.getTos().toArray(new String[0]));
			}else {
				 message.setTo(emailContainer.getTo());
			}
			if(emailContainer.getCcs() != null && !emailContainer.getCcs().isEmpty()) {
				message.setCc(emailContainer.getCcs().toArray(new String[0]));
			}
			if(emailContainer.getBccs() != null && !emailContainer.getBccs().isEmpty()) {
				message.setCc(emailContainer.getBccs().toArray(new String[0]));
			}
	        
			message.setText(emailContainer.getContent(), true);  /* isHtml */

	        mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			status = false;
		}
        
		return status;
	}
}
