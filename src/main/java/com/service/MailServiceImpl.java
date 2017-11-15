package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service("mailService")
public class MailServiceImpl implements MailService {
    private final static String MAIL_TEMPLATE = "mailTemplate";
    private final static String MAIL_TO = "damianos-11@o2.pl";

    private TemplateEngine templateEngine;
    private JavaMailSender mailSender;

    @Autowired
    public MailServiceImpl(
            TemplateEngine templateEngine,
            JavaMailSender mailSender
    ) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendMail(String subject, MailContext mailContext) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(MailServiceImpl.MAIL_TO);
        helper.setSubject(subject);
        helper.setText(this.getRenderTemplate(mailContext.setContext()), true);
        mailSender.send(message);
    }

    private String getRenderTemplate(Context context) {
        return templateEngine.process(MailServiceImpl.MAIL_TEMPLATE, context);
    }
}