package com.huskyshare.backend.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

@Component
@Scope(scopeName = "singleton")
public class EmailHandler {

    private final static String emailAccount = "";   //Email goes here
    private final static String emailPassword = "";  //Pwd goes here
    private final static String gmailHost = "smtp.gmail.com";

    @Autowired
    private RedisHandler redisHandler;

    public String sendCode(String address) throws Exception {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", gmailHost);
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(emailAccount,emailPassword);
            }
        });
        session.setDebug(true);

        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(emailAccount, "HuskyShare2019:)", "UTF-8"));

        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(address, "USER_CC", "UTF-8"));

        message.setSubject("Signing up for HuskyShare :)");

        int code = (int)(Math.random()*10000);

        redisHandler.store(address.toLowerCase(), code + "");

        message.setContent("Hello husky!\nHere is your code: " + code, "text/html;charset=UTF-8");

        message.setSentDate(new Date());

        //message.saveChanges();

        Transport.send(message);

        return "SEND_SUCCESS";
    }

    public boolean compareCode(String address, String code){ return redisHandler.get(address.toLowerCase()).equals(code); }

    @Bean
    public EmailHandler getEmailHandler(){ return new EmailHandler();}

}
