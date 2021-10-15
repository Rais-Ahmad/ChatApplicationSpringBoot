package com.chatapplicationspringBoot.Repository;

import com.chatapplicationspringBoot.Model.Mail;

import javax.mail.MessagingException;

    public interface SendMailService {

        void sendMail(Mail mail);

        void sendMailWithAttachments(Mail mail) throws MessagingException;
    }

