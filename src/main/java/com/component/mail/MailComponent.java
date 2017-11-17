package com.component.mail;

import javax.mail.MessagingException;

public interface MailComponent {
    public void sendMail(String subject, MailContext mailContext) throws MessagingException;
}
