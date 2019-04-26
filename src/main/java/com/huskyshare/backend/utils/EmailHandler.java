package com.huskyshare.backend.utils;

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

public class EmailHandler {

    public final static String emailAccount = "dylanisabear@gmail.com";
    public final static String emailPassword = "20001018lyh1";
    public final static String gmailHost = "smtp.gmail.com";

    public String sendCode(String address) throws Exception {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
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

        message.setContent("Hello husky!\nHere is your code: " + code, "text/html;charset=UTF-8");

        message.setSentDate(new Date());

        //message.saveChanges();

        Transport.send(message);

        return "SEND_SUCCESS";
    }

    public static void main(String args[]){
        EmailHandler emailHandler = new EmailHandler();
        try {
            System.out.println(emailHandler.sendCode("tinachoo1104@163.com"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
