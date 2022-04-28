package org.emarket.hustle.emarkethustle.service;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Service
public class EmailSenderService
{
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Configuration configuration;

	public void sendEmail(
			String toEmail,
			String subject,
			String body)
	{
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("${spring.mail.username}");
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(body);

		mailSender.send(message);
		System.out.println("Message Sent Successfully");
	}

	public void sendEmailWithTemplate(
			Map<String, Object> model,
			String toEmail,
			String subject) throws MessagingException, TemplateNotFoundException, MalformedTemplateNameException,
			ParseException, IOException, TemplateException
	{
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,
				MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);

		Template template = configuration.getTemplate("email-template-final.ftl");
		String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

		mimeMessageHelper.setFrom("${spring.mail.username}");
		mimeMessageHelper.setTo(toEmail);
		mimeMessageHelper.setSubject(subject);
		mimeMessageHelper.setText(html, true);
		mailSender.send(mimeMessage);
	}
}
