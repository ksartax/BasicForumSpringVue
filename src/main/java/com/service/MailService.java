package com.service;

import javax.mail.MessagingException;

public interface MailService {
    public void sendMail(String subject, MailContext mailContext) throws MessagingException;
}
